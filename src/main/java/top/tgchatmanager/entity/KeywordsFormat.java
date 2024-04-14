package top.tgchatmanager.entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class KeywordsFormat {
    private String uuid;
    private String regex;
    private String replyText;

    private List<String> keywordsButtons = new ArrayList<>();
    private String photoUrl;
    private String videoUrl;

    private Map<String, String> ruleMap = new ConcurrentHashMap<>();

    public KeywordsFormat(){

    }

    public KeywordsFormat(String data) {
        String[] keyValuePair = data.split(" \\| ");
        this.uuid = keyValuePair[0];

        String[] parts = keyValuePair[1].split("===");
        this.regex = parts[0];
        String[] actions = parts[1].split("&&");
        this.replyText = actions[0];
        for (String action : actions) {
            if (action.startsWith("btns=")) {
                String[] btnParts = action.substring(5).split("\\$\\$\\$");
                keywordsButtons.addAll(Arrays.asList(btnParts));
            } else if (action.startsWith("photo=")) {
                this.photoUrl = action.substring(6);
            } else if (action.startsWith("video=")) {
                this.videoUrl = action.substring(6);
            } else if (action.startsWith("del=")) {
                ruleMap.put("DeleteAfterXSeconds", action.substring(4).split("、")[0].split("=")[1]);
                ruleMap.put("DeleteReplyAfterYSeconds", action.substring(4).split("、")[1].split("=")[1]);
            } else if (action.startsWith("welcome=")){
                ruleMap.put("DelWelcome",action.substring(8));
            } else if (action.startsWith("intoGroupBan=")) {
                ruleMap.put("DelIntoGroupBan",action.substring(13));
            } else if (action.startsWith("crontab=")) {
                ruleMap.put("crontabOption",action.substring(8));
            }
        }
    }

    // Getter methods...

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public String getRegex() {
        return regex;
    }

    public String getReplyText() {
        return replyText;
    }

    public List<String> getKeywordsButtons() {
        return keywordsButtons;
    }

    public Map<String, String> getRuleMap() {
        return ruleMap;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public void setKeywordsButtons(List<String> keywordsButtons) {
        this.keywordsButtons = keywordsButtons;
    }

    public void setRuleMap(Map<String, String> map) {
        this.ruleMap = map;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "KeywordsFormat{" +
                "uuid='" + uuid + '\'' +
                ", regex='" + regex + '\'' +
                ", replyText='" + replyText + '\'' +
                ", keywordsButtons=" + keywordsButtons +
                ", photoUrl='" + photoUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", map=" + ruleMap +
                '}';
    }
}
