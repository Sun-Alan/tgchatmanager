package top.tgchatmanager.handleService;


import com.alibaba.fastjson2.JSONObject;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import top.tgchatmanager.entity.BaseInfo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GoogleCloudVisionService {

    public File downloadFileWithOkHttp(String url) {
        final int MAX_RETRIES = 5;
        int attempt = 0;

        while (attempt < MAX_RETRIES) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("下载文件失败: " + response);
                }

                File tempFile = Files.createTempFile("prefix-", ".suffix").toFile();
                if (response.body() != null) {
                    try (FileOutputStream fos = new FileOutputStream(tempFile);
                         InputStream is = response.body().byteStream()) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                }
                return tempFile;
            } catch (Exception e) {
                attempt++;
                log.error("下载文件失败，尝试次数：{}，原因：{}", attempt, e.getMessage(), e);
                if (attempt >= MAX_RETRIES) {
                    log.error("达到最大尝试次数，停止重试。");
                    return null;
                }
            }
        }
        return null;  // 当达到最大尝试次数时返回null
    }

    private ImageAnnotatorClient createClient() {
        try {
            // 使用BaseInfo读取的配置信息来创建Google Vision客户端
            JSONObject googleServiceAccountConfig = BaseInfo.getGoogleServiceAccountConfig();
            Credentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(googleServiceAccountConfig.toString().getBytes()));
            return ImageAnnotatorClient.create(ImageAnnotatorSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build());
        } catch (IOException e) {
            log.error("读取本地配置创建Google Vision客户端失败，请检查配置是否正确");
            return null;
        }
    }

    public List<EntityAnnotation> detectTextFromRemoteImage(String imageUrl) {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        ImageSource imgSource = ImageSource.newBuilder().setImageUri(imageUrl).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build();
        requests.add(request);

        try (ImageAnnotatorClient client = createClient()) {
            if (client == null) return new ArrayList<>();

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            AnnotateImageResponse res = response.getResponses(0);

            if (res.hasError()) {
                return new ArrayList<>();
            }

            return res.getTextAnnotationsList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public SafeSearchAnnotation detectSafeSearchFromRemoteImage(String imageUrl) {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        ImageSource imgSource = ImageSource.newBuilder().setImageUri(imageUrl).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.SAFE_SEARCH_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build();
        requests.add(request);

        try (ImageAnnotatorClient client = createClient()) {
            if (client == null) return null;

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            AnnotateImageResponse res = response.getResponses(0);

            if (res.hasError()) {

                return null;
            }

            return res.getSafeSearchAnnotation();
        } catch (Exception e) {

            return null;
        }
    }

    public List<EntityAnnotation> detectTextFromLocalImage(File tempFile) {
        final int MAX_RETRIES = 5;
        int attempt = 0;

        while (attempt < MAX_RETRIES) {
            List<AnnotateImageRequest> requests = new ArrayList<>();

            try {
                ByteString imgBytes = ByteString.readFrom(Files.newInputStream(tempFile.toPath()));
                Image img = Image.newBuilder().setContent(imgBytes).build();
                Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                        .addFeatures(feat)
                        .setImage(img)
                        .build();
                requests.add(request);

                try (ImageAnnotatorClient client = createClient()) {
                    if (client == null) return new ArrayList<>();

                    BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                    AnnotateImageResponse res = response.getResponses(0);

                    if (res.hasError()) {
                        return new ArrayList<>();
                    }

                    return res.getTextAnnotationsList();
                }
            } catch (Exception e) {
                attempt++;
                log.error("解析图像文本失败，尝试次数：{}，原因：{}", attempt, e.getMessage(), e);
                if (attempt >= MAX_RETRIES) {
                    log.error("达到最大尝试次数，停止重试。");
                    return new ArrayList<>();
                }
            }
        }

        return new ArrayList<>();  // 当达到最大尝试次数时返回空列表
    }

    public SafeSearchAnnotation detectSafeSearchFromLocalImage(File tempFile) {
        final int MAX_RETRIES = 3;
        int attempt = 0;

        while (attempt < MAX_RETRIES) {
            List<AnnotateImageRequest> requests = new ArrayList<>();

            try {
                ByteString imgBytes = ByteString.readFrom(Files.newInputStream(tempFile.toPath()));
                Image img = Image.newBuilder().setContent(imgBytes).build();
                Feature feat = Feature.newBuilder().setType(Feature.Type.SAFE_SEARCH_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                        .addFeatures(feat)
                        .setImage(img)
                        .build();
                requests.add(request);

                try (ImageAnnotatorClient client = createClient()) {
                    if (client == null) return null;

                    BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                    AnnotateImageResponse res = response.getResponses(0);

                    if (res.hasError()) {
                        return null;
                    }

                    return res.getSafeSearchAnnotation();
                }
            } catch (Exception e) {
                attempt++;
                log.error("图片分级失败，尝试次数：{}，原因：{}", attempt, e.getMessage(), e);
                if (attempt >= MAX_RETRIES) {
                    log.error("达到最大尝试次数，停止重试。");
                    return null;
                }
            }
        }

        return null;  // 当达到最大尝试次数时返回null
    }

}
