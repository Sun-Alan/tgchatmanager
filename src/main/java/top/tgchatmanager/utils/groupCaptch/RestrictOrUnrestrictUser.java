package top.tgchatmanager.utils.groupCaptch;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.RestrictChatMember;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.ChatPermissions;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import top.tgchatmanager.entity.BaseInfo;
import top.tgchatmanager.entity.KeywordsFormat;
import top.tgchatmanager.utils.CheckUser;
import top.tgchatmanager.utils.SendContent;
import top.tgchatmanager.utils.TimerDelete;
import top.tgchatmanager.utils.userNameToUserId.ObtainUserId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class RestrictOrUnrestrictUser {

    @Autowired
    private TimerDelete timerDelete;

    @Autowired
    private CheckUser checkUser;

    @Autowired
    private SendContent sendContent;

    @Autowired
    private ObtainUserId obtainUserId;

    @Autowired
    private CaptchaManagerCacheMap captchaManagerCacheMap;


    private void unMuteFunc(AbsSender sender, Update update, Long userId, String firstName, String chatId) {
        if (unrestrictUser(sender, userId, chatId)) {
            KeywordsFormat keywordsFormat = new KeywordsFormat();
            String text1 = String.format("用户 <b><a href=\"tg://user?id=%d\">%s</a></b> 已被管理员解除限制。", userId, firstName);
            keywordsFormat.setReplyText(text1);
            timerDelete.sendTimedMessage(sender, sendContent.createResponseMessage(update, keywordsFormat, "html"), 60);
        }
    }

    private void commonFunc(AbsSender sender, Update update, Long userId, String firstName, String chatId, String text, String biaoshi) {
        String muteReason = "";
        Long secondsToAdd = null;
        if (text.split(" ").length >= 3 && "noreply".equals(biaoshi)) {
            try {
                secondsToAdd = Long.valueOf(text.split(" ")[2]);
                if (text.split(" ").length >= 4) {
                    muteReason = text.split(" ")[3];
                }
            } catch (NumberFormatException e) {
                muteReason = text.split(" ")[2];
            }
        } else if (text.split(" ").length >= 2 && "reply".equals(biaoshi)) {
            try {
                secondsToAdd = Long.valueOf(text.split(" ")[1]);
                if (text.split(" ").length >= 3) {
                    muteReason = text.split(" ")[2];
                }
            } catch (NumberFormatException e) {
                muteReason = text.split(" ")[1];
            }
        }
        if (secondsToAdd == null || secondsToAdd <= 30L) {
            secondsToAdd = 0L;
        }
        if (restrictUser(sender, userId, chatId,secondsToAdd)) {
            KeywordsFormat keywordsFormat = new KeywordsFormat();
            String text1 = String.format("用户 <b><a href=\"tg://user?id=%d\">%s</a></b> 已被管理员禁言。", userId, firstName);
            if (StringUtils.hasText(muteReason)) {
                text1 += "\n" + String.format("禁言原因：<b>%s</b>！", muteReason);
            }
            if (secondsToAdd != 0L) {
                text1 += "\n" + String.format("禁言时间：<b>%s秒</b>！", secondsToAdd);
            } else {
                text1 += "\n" + "禁言时间：<b>永久</b>！";
            }
            List<String> keywordsButtons = new ArrayList<>();
            keywordsButtons.add("👥管理员解禁##adminUnmute" + userId);
            keywordsFormat.setKeywordsButtons(keywordsButtons);
            keywordsFormat.setReplyText(text1);
            String messageId = timerDelete.sendTimedMessage(sender, sendContent.createResponseMessage(update, keywordsFormat, "html"), 60);
            captchaManagerCacheMap.updateUserMapping(userId.toString(), chatId, 0, Integer.valueOf(messageId));
        }
    }

    public boolean muteOption(AbsSender sender, Update update) throws TelegramApiException {
        String text = update.getMessage().getText().trim();
        String chatId = update.getMessage().getChatId().toString();
        Integer oldMessageId = update.getMessage().getMessageId();

        if (checkUser.isGroupAdmin(sender, update)) {
            if (text.startsWith("!mute") || text.startsWith("!mute@" + BaseInfo.getBotName())
                    || text.startsWith("/mute") || text.startsWith("/mute@" + BaseInfo.getBotName())
            ) {
                if (update.getMessage().hasEntities() && !"bot_command".equals(update.getMessage().getEntities().get(update.getMessage().getEntities().size() - 1).getType())) {
                    MessageEntity messageEntity = update.getMessage().getEntities().get(update.getMessage().getEntities().size() - 1);
                    if (text.split(" ").length >= 2 && text.split(" ")[1].contains("@") && "mention".equals(messageEntity.getType())) {
                        JSONObject jsonObject = obtainUserId.fetchUserWithOkHttp(messageEntity.getText());
                        Long userNameToId = jsonObject.getLong("id");
                        String userNameToFirstName = jsonObject.getString("first_name");
                        commonFunc(sender, update, userNameToId, userNameToFirstName, chatId, text, "noreply");
                    } else if (text.split(" ").length >= 2 && "text_mention".equals(messageEntity.getType())) {
                        Long userId = messageEntity.getUser().getId();
                        String firstName = messageEntity.getUser().getFirstName();
                        commonFunc(sender, update, userId, firstName, chatId, text, "noreply");
                    }
                } else if (text.split(" ").length >= 2 && update.getMessage().getReplyToMessage() == null) {
                    Long userId = Long.valueOf(text.split(" ")[1]);
                    GetChatMember getChatMember = new GetChatMember();
                    getChatMember.setUserId(userId);
                    getChatMember.setChatId(chatId);
                    ChatMember chatMember = sender.execute(getChatMember);
                    String firstName = chatMember.getUser().getFirstName();
                    commonFunc(sender, update, userId, firstName, chatId, text, "noreply");
                } else if (update.getMessage().getReplyToMessage() != null) {
                    Long userId = update.getMessage().getReplyToMessage().getFrom().getId();
                    String firstName = update.getMessage().getReplyToMessage().getFrom().getFirstName();
                    commonFunc(sender, update, userId, firstName, chatId, text, "reply");
                }
                sender.execute(new DeleteMessage(chatId, oldMessageId));
                return true;
            }
        } else if (text.startsWith("!mute") || text.startsWith("!mute@" + BaseInfo.getBotName())
                || text.startsWith("/mute") || text.startsWith("/mute@" + BaseInfo.getBotName())
        ) {
            sender.execute(new DeleteMessage(chatId, oldMessageId));
            return true;
        }
        return false;

    }


    public boolean unMuteOption(AbsSender sender, Update update) throws TelegramApiException {
        String text = update.getMessage().getText().trim();
        String chatId = update.getMessage().getChatId().toString();
        Integer oldMessageId = update.getMessage().getMessageId();

        if (checkUser.isGroupAdmin(sender, update)) {
            if (text.startsWith("!unmute") || text.startsWith("!unmute@" + BaseInfo.getBotName())
                    || text.startsWith("/unmute") || text.startsWith("/unmute@" + BaseInfo.getBotName())
            ) {
                if (update.getMessage().hasEntities() && !"bot_command".equals(update.getMessage().getEntities().get(update.getMessage().getEntities().size() - 1).getType())) {
                    MessageEntity messageEntity = update.getMessage().getEntities().get(update.getMessage().getEntities().size() - 1);
                    if (text.split(" ").length >= 2 && text.split(" ")[1].contains("@") && "mention".equals(messageEntity.getType())) {
                        JSONObject jsonObject = obtainUserId.fetchUserWithOkHttp(messageEntity.getText());
                        Long userNameToId = jsonObject.getLong("id");
                        String userNameToFirstName = jsonObject.getString("first_name");
                        unMuteFunc(sender, update, userNameToId, userNameToFirstName, chatId);
                    } else if (text.split(" ").length >= 2 && "text_mention".equals(messageEntity.getType())) {
                        Long userId = messageEntity.getUser().getId();
                        String firstName = messageEntity.getUser().getFirstName();
                        unMuteFunc(sender, update, userId, firstName, chatId);
                    }
                } else if (text.split(" ").length >= 2 && update.getMessage().getReplyToMessage() == null) {
                    Long userId = Long.valueOf(text.split(" ")[1]);
                    GetChatMember getChatMember = new GetChatMember();
                    getChatMember.setUserId(userId);
                    getChatMember.setChatId(chatId);
                    ChatMember chatMember = sender.execute(getChatMember);
                    String firstName = chatMember.getUser().getFirstName();
                    unMuteFunc(sender, update, userId, firstName, chatId);
                } else if (update.getMessage().getReplyToMessage() != null) {
                    Long userId = update.getMessage().getReplyToMessage().getFrom().getId();
                    String firstName = update.getMessage().getReplyToMessage().getFrom().getFirstName();
                    unMuteFunc(sender, update, userId, firstName, chatId);
                }
                sender.execute(new DeleteMessage(chatId, oldMessageId));
                return true;
            }
        } else if (text.startsWith("!unmute") || text.startsWith("!unmute@" + BaseInfo.getBotName())
                || text.startsWith("/unmute") || text.startsWith("/unmute@" + BaseInfo.getBotName())
        ) {
            sender.execute(new DeleteMessage(chatId, oldMessageId));
            return true;
        }
        return false;

    }


    public boolean restrictUser(AbsSender sender, Long userId, String chatId,Long secondsToAdd) {
        RestrictChatMember restrictChatMember = new RestrictChatMember();
        restrictChatMember.setChatId(chatId);
        restrictChatMember.setUserId(userId);
        restrictChatMember.setUntilDateDateTime(ZonedDateTime.now().plusSeconds(secondsToAdd));

        ChatPermissions chatPermissions = new ChatPermissions();
        chatPermissions.setCanSendOtherMessages(false);
        restrictChatMember.setPermissions(chatPermissions);

        try {
            sender.execute(restrictChatMember);
            return true;
        } catch (TelegramApiException e) {
            log.error("禁言用户失败，{}",e.getMessage(),e);
            return false;
        }
    }

    public boolean unrestrictUser(AbsSender sender, Long userId, String chatId) {
        RestrictChatMember restrictChatMember = new RestrictChatMember();
        restrictChatMember.setChatId(chatId);
        restrictChatMember.setUserId(userId);

        ChatPermissions permissions = new ChatPermissions();
        permissions.setCanSendMessages(true);
        permissions.setCanSendPolls(true);
        permissions.setCanSendOtherMessages(true);
        permissions.setCanAddWebPagePreviews(true);
        permissions.setCanChangeInfo(true);
        permissions.setCanInviteUsers(true);
        permissions.setCanPinMessages(true);

        restrictChatMember.setPermissions(permissions);

        try {
            sender.execute(restrictChatMember); // Execute the Telegram API method
            return true;
        } catch (Exception e) {
            log.error("解禁用户失败，{}",e.getMessage(),e);
            return false;
        }
    }

}