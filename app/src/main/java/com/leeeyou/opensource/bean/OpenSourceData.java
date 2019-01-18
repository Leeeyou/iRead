package com.leeeyou.opensource.bean;

public class OpenSourceData {
    private String name;
    private String url;
    private String desc;

    public OpenSourceData(String name, String url, String desc) {
        this.name = name;
        this.url = url;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
