package top.tgchatmanager.wordCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 记录发言内容和次数
    public void recordMessage(Long chatId, String chatName, Long userId, String userName, String message, String date) {
        if (StringUtils.hasText(message)) { // 仅在消息非空时添加
            if (!StringUtils.hasText(userName)){
                userName = String.valueOf(userId);
            }
            String keySpeak = String.format("%s_%s_%s-%s_%s_发言数", chatId, chatName, userId, userName, date);
            redisTemplate.opsForZSet().incrementScore(keySpeak, "score", 1);
            redisTemplate.expire(keySpeak, 30, TimeUnit.DAYS);

            String keyContent = String.format("%s_%s_%s-%s_%s_发言内容", chatId, chatName, userId, userName, date);
            redisTemplate.opsForZSet().add(keyContent, message, System.currentTimeMillis());
            redisTemplate.expire(keyContent, 30, TimeUnit.DAYS);
        }
    }

    // 获取指定日期范围的所有消息
    public Map<String, Object> getAllMessagesInfoForDate(String chatId, String chatName, String date) {
        Map<String, Object> result = new HashMap<>();
        Set<String> allMessages = new HashSet<>();

        // 使用日期直接构建键模式，这样可以避免不必要的键匹配
        String keyPattern = String.format("%s_%s_*_%s_发言内容", chatId, chatName, date);
        Set<String> keys = redisTemplate.keys(keyPattern);

        int totalUsers = 0;
        int totalMessages = 0;

        if (keys != null) {
            totalUsers = keys.size(); // 统计发言的人数
            for (String key : keys) {
                Set<String> messages = redisTemplate.opsForZSet().range(key, 0, -1);
                if (messages != null) {
                    allMessages.addAll(messages);
                }
                totalMessages += redisTemplate.opsForZSet().zCard(key).intValue(); // 获取 ZSet 的大小，即用户的发言数
            }
        }

        result.put("allMessages", allMessages);
        result.put("totalUsers", totalUsers);
        result.put("totalMessages", totalMessages);

        return result;
    }

    public Map<String, Integer> getTopSpeakersForDate(String chatId, String chatName, String date, int topN) {
        Map<String, Integer> userSpeakCounts = new HashMap<>();

        String keyPattern = String.format("%s_%s_*_%s_发言数", chatId, chatName, date);
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null) {
            for (String key : keys) {
                Double score = redisTemplate.opsForZSet().score(key, "score");
                if (score != null) {
                    String[] userInfo = key.split("_")[2].split("-");
                    String userName = userInfo[0];
                    if (userInfo.length >= 2) {
                        userName = userInfo[1];
                    }
                    userSpeakCounts.put(userName, score.intValue());
                }
            }
        }

        // 对map按值排序
        return userSpeakCounts.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed())
                .limit(topN)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}