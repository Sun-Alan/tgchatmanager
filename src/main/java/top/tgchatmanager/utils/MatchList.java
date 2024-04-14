package top.tgchatmanager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import top.tgchatmanager.entity.GroupInfoWithBLOBs;
import top.tgchatmanager.entity.KeywordsFormat;
import top.tgchatmanager.sqlService.GroupInfoService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatchList {

    @Autowired
    private GroupInfoService groupInfoService;

    public List<KeywordsFormat> createBanKeyDeleteOptionList(Update update) {
        GroupInfoWithBLOBs groupInfoWithBLOBs = groupInfoService.selAllByGroupId(update.getMessage().getChatId().toString());
        if (groupInfoWithBLOBs != null) {
            String content = groupInfoWithBLOBs.getKeywords();
            if (content != null && !content.isEmpty()) {
                return Arrays.stream(content.split("\\n{2,}"))
                        .map(String::trim)
                        .map(KeywordsFormat::new)
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}
