package top.tgchatmanager.utils.userNameToUserId;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import top.tgchatmanager.entity.BaseInfo;

import java.io.IOException;

@Component
@Slf4j
public class ObtainUserId {
    public JSONObject fetchUserWithOkHttp(String userName) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BaseInfo.getApiServer() + "/api/getInfo/?id=" + userName)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String jsonStr = response.body().string();
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                return jsonObject.getJSONObject("response").getJSONObject("User");
            }
        } catch (IOException e) {
            log.error("从userName获取userId失败");
        }
        return null;
    }
}
