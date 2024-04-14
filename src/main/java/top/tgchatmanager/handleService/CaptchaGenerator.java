package top.tgchatmanager.handleService;

import com.pig4cloud.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import top.tgchatmanager.entity.GroupInfoWithBLOBs;
import top.tgchatmanager.entity.KeywordsFormat;
import top.tgchatmanager.sqlService.BotRecordService;
import top.tgchatmanager.sqlService.GroupInfoService;
import top.tgchatmanager.utils.SendContent;
import top.tgchatmanager.utils.TimerDelete;
import top.tgchatmanager.utils.groupCaptch.CaptchaManager;
import top.tgchatmanager.utils.groupCaptch.CaptchaManagerCacheMap;
import top.tgchatmanager.utils.groupCaptch.GroupMessageIdCacheMap;
import top.tgchatmanager.utils.groupCaptch.RestrictOrUnrestrictUser;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaptchaGenerator implements CaptchaService {

    @Autowired
    private GroupInfoService groupInfoService;

    @Autowired
    private BotRecordService botRecordService;

    @Autowired
    private SendContent sendContent;

    @Autowired
    private CaptchaManager captchaManager;

    @Autowired
    private CaptchaManagerCacheMap captchaManagerCacheMap;

    @Autowired
    private RestrictOrUnrestrictUser restrictOrUnrestrictUser;

    @Autowired
    private TimerDelete timerDelete;

    @Autowired
    private GroupMessageIdCacheMap groupMessageIdCacheMap;


    @Override
    public void sendCaptcha(AbsSender sender, Update update, String chatId) throws TelegramApiException {

        Long userId = update.getMessage().getFrom().getId();
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(3);


        // 保存验证码的文本值以供后续比较
        captchaManager.updateUserMapping(userId.toString(), chatId, captcha.text());


        // 使用Base64获取验证码图像的InputStream
        String base64Image = captcha.toBase64();
        base64Image = base64Image.replace("data:image/png;base64,", ""); // 移除前缀
        ByteArrayInputStream captchaStream = base64ToInputStream(base64Image);

        // 发送验证码图像到用户
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId());
        sendPhoto.setPhoto(new InputFile(captchaStream, "captcha.png"));
        String text = String.format("请 <b><a href=\"tg://user?id=%d\">%s</a></b> 在 <b>90秒内</b> 输入计算结果，超时将永久限制发言！", userId, update.getMessage().getChat().getFirstName());
        sendPhoto.setCaption(text);
        sendPhoto.setParseMode(ParseMode.HTML);
        sender.execute(sendPhoto);
    }

    @Override
    public void answerReplyhandle(AbsSender sender, Update update) throws TelegramApiException {
        String userAnswer = update.getMessage().getText();
        String userId = update.getMessage().getFrom().getId().toString();
        String groupId = captchaManager.getGroupIdForUser(userId);
        Integer messageId = captchaManagerCacheMap.getMessageIdForUser(userId, groupId);
        Integer attempt = captchaManagerCacheMap.getAttemptForUser(userId, groupId);
        String correctAnswer = captchaManager.getAnswerForUser(userId);
        GroupInfoWithBLOBs groupInfoWithBLOBs = groupInfoService.selAllByGroupId(groupId);
        if (StringUtils.hasText(userAnswer) && !correctAnswer.isEmpty()) {
            if (userAnswer.equals(correctAnswer)) {
                SendMessage message = sendContent.messageText(update, "验证通过，现在你可以在群里自由发言了");
                sender.execute(message);
                restrictOrUnrestrictUser.unrestrictUser(sender, update.getMessage().getFrom().getId(), groupId);
                botRecordService.addUserRecord(groupId, userId, update.getMessage().getDate().toString());
                if (groupInfoWithBLOBs != null && "open".equals(groupInfoWithBLOBs.getIntogroupwelcomeflag())) {
                    if (groupMessageIdCacheMap.getMapSize() > 0) {
                        groupMessageIdCacheMap.deleteAllMessage(sender, groupId);
                    }
                    if (StringUtils.hasText(groupInfoWithBLOBs.getKeywords()) && groupInfoWithBLOBs.getKeywords().contains("&&welcome=")) {
                        List<KeywordsFormat> keywordsFormatList = Arrays.stream(groupInfoWithBLOBs.getKeywords().split("\\n{2,}"))
                                .map(String::trim)
                                .map(KeywordsFormat::new)
                                .collect(Collectors.toList());
                        for (KeywordsFormat keywordFormat : keywordsFormatList) {
                            Map<String, String> currentMap = keywordFormat.getRuleMap();
                            if (currentMap.containsKey("DelWelcome")) {
                                KeywordsFormat newKeyFormat = new KeywordsFormat();
                                newKeyFormat.setKeywordsButtons(keywordFormat.getKeywordsButtons());
                                String text = keywordFormat.getReplyText()
                                        .replaceAll("@userId", String.format("<b><a href=\"tg://user?id=%d\">%s</a></b>", update.getMessage().getFrom().getId(), update.getMessage().getFrom().getFirstName()))
                                        .replaceAll("@groupName", String.format("<b>%s</b>", groupInfoWithBLOBs.getGroupname()));
                                newKeyFormat.setReplyText(text);
                                if (keywordFormat.getVideoUrl() != null) {
                                    newKeyFormat.setVideoUrl(keywordFormat.getVideoUrl());
                                } else if (keywordFormat.getPhotoUrl() != null) {
                                    newKeyFormat.setPhotoUrl(keywordFormat.getPhotoUrl());
                                }
                                Object response = sendContent.createGroupMessage(groupId, newKeyFormat, "html");
                                Integer msgId;
                                if (keywordFormat.getPhotoUrl() != null) {
                                    SendPhoto sendPhoto = (SendPhoto) response;
                                    msgId = timerDelete.deleteMessageImmediatelyAndNotifyAfterDelay(sender, sendPhoto, groupId, messageId, update.getMessage().getFrom().getId(), Integer.parseInt(currentMap.get("DelWelcome")));
                                } else if (keywordFormat.getVideoUrl() != null) {
                                    SendVideo sendVideo = (SendVideo) response;
                                    msgId = timerDelete.deleteMessageImmediatelyAndNotifyAfterDelay(sender, sendVideo, groupId, messageId, update.getMessage().getFrom().getId(), Integer.parseInt(currentMap.get("DelWelcome")));
                                } else {
                                    SendMessage sendMessage = (SendMessage) response;
                                    sendMessage.setDisableWebPagePreview(true);
                                    msgId = timerDelete.deleteMessageImmediatelyAndNotifyAfterDelay(sender, sendMessage, groupId, messageId, update.getMessage().getFrom().getId(), Integer.parseInt(currentMap.get("DelWelcome")));
                                }
                                groupMessageIdCacheMap.setGroupMessageId(groupId, msgId);
                            }
                        }
                        return;
                    }
                }
                String text = String.format("用户 <b><a href=\"tg://user?id=%d\">%s</a></b> 验证通过，解除群组限制！", update.getMessage().getFrom().getId(), update.getMessage().getFrom().getFirstName());
                SendMessage notification = new SendMessage();
                notification.setChatId(groupId);
                notification.setText(text);
                notification.setParseMode(ParseMode.HTML);
                timerDelete.deleteMessageImmediatelyAndNotifyAfterDelay(sender, notification, groupId, messageId, update.getMessage().getFrom().getId(), 10);
            } else {
                if (attempt != null) {
                    captchaManagerCacheMap.updateUserMapping(userId, groupId, attempt + 1, messageId);
                    if (attempt >= 1) {
                        sender.execute(sendContent.messageText(update, "未通过验证，你的机会已经用尽！"));
                        timerDelete.deleteByMessageIdImmediately(sender, groupId, messageId);
                        captchaManager.clearMappingsForUser(userId);
                        captchaManagerCacheMap.clearMappingsForUser(userId, groupId);
                        return;
                    }
                    SendMessage message = sendContent.messageText(update, "未通过验证，请再试一次，你只有两次机会，次数用尽/超时都将会永久禁言");
                    sender.execute(message);
                }
            }
        }
    }


    private ByteArrayInputStream base64ToInputStream(String base64) {

        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return new ByteArrayInputStream(decodedBytes);
    }
}
