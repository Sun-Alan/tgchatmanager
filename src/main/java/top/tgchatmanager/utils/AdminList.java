package top.tgchatmanager.utils;

import org.springframework.stereotype.Component;
import org.springframework.cache.annotation.Cacheable;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class AdminList {

    @Cacheable(value = "adminsCache", key = "#chatId + 'admins'")
    public List<ChatMember> getAdmins(AbsSender sender,String chatId) throws TelegramApiException {
        GetChatAdministrators getChatAdministrators = new GetChatAdministrators();
        getChatAdministrators.setChatId(chatId);
        return sender.execute(getChatAdministrators);
    }

}