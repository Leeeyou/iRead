package com.example.leeeyou.zhihuribao.data.model;

import com.google.gson.Gson;

import java.util.List;

public class Story {
    public List<String> images;
    public int type;
    public int id;
    public String ga_prefix;
    public String title;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
