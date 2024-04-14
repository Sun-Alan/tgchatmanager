package top.tgchatmanager.scheduledTasks;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.bots.AbsSender;
import top.tgchatmanager.entity.GroupInfoWithBLOBs;
import top.tgchatmanager.entity.KeywordsFormat;
import top.tgchatmanager.sqlService.GroupInfoService;
import top.tgchatmanager.utils.SendContent;
import top.tgchatmanager.utils.TimerDelete;

import java.util.List;

@Component
@Slf4j
public class ForBidMedia implements Job {

    @Autowired
    private SendContent sendContent;

    @Autowired
    private TimerDelete timerDelete;

    @Autowired
    private GroupInfoService groupInfoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws RuntimeException {
        log.warn("执行开启夜间模式");
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        AbsSender sender = (AbsSender) dataMap.get("sender");
        String groupId = dataMap.getString("groupId");
        int delMessageTime = dataMap.getInt("delMessageTime");
        KeywordsFormat keywordsFormat = new KeywordsFormat();
        keywordsFormat.setKeywordsButtons((List<String>) dataMap.get("keyButtons"));
        keywordsFormat.setReplyText(dataMap.getString("text"));
        timerDelete.sendTimedMessage(sender, sendContent.createGroupMessage(groupId, keywordsFormat, "html"), delMessageTime);
        GroupInfoWithBLOBs groupInfoWithBLOBs = new GroupInfoWithBLOBs();
        groupInfoWithBLOBs.setCansendmediaflag("open");
        if (groupInfoService.updateSelectiveByChatId(groupInfoWithBLOBs, groupId)) {
            log.warn("夜间模式开启成功");
        }
    }
}
