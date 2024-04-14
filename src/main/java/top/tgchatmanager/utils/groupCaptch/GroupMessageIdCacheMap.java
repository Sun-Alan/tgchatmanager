package top.tgchatmanager.utils.groupCaptch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.bots.AbsSender;
import top.tgchatmanager.utils.TimerDelete;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GroupMessageIdCacheMap {

    @Autowired
    private TimerDelete timerDelete;

    private final Map<String, Integer> groupMessageIdManager = new ConcurrentHashMap<>();

    public void setGroupMessageId(String groupId, Integer messageId) {
        groupMessageIdManager.put(groupId + "|" + messageId, messageId);
    }

    public int getMapSize() {
        return groupMessageIdManager.size();
    }

    public void deleteAllMessage(AbsSender sender, String chatId) {
        groupMessageIdManager.entrySet().removeIf(entry -> {
            timerDelete.deleteByMessageIdImmediately(sender, chatId, entry.getValue());
            return true;
        });
    }

}