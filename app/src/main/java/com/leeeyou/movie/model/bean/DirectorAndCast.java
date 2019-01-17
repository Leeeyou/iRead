package com.leeeyou.movie.model.bean;

public class DirectorAndCast {
    private String alt;
    private String avatar;
    private String name;
    private String id;
    private boolean isDirector;

    public DirectorAndCast(String alt, String avatar, String name, String id, boolean isDirector) {
        this.alt = alt;
        this.avatar = avatar;
        this.name = name;
        this.id = id;
        this.isDirector = isDirector;
    }

    public String getAlt() {
        return alt;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isDirector() {
        return isDirector;
    }
}
