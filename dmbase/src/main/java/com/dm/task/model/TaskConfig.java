package com.dm.task.model;

public class TaskConfig {
    private String id;

    private Boolean isKeywords;

    private String keywords;

    private Boolean isVisited;

    private String channelIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Boolean getIsKeywords() {
        return isKeywords;
    }

    public void setIsKeywords(Boolean isKeywords) {
        this.isKeywords = isKeywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Boolean isVisited) {
        this.isVisited = isVisited;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds == null ? null : channelIds.trim();
    }
}