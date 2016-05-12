package com.example.leeeyou.zhihuribao.data.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by leeeyou on 16/5/12.
 * <p>
 * 日报详情的数据结构
 */
public class StoryDetail {
    public String body;
    public String image_source;
    public String title;
    public String image;
    public String share_url;
    public List<String> js;
    public String ga_prefix;
    public List<String> images;
    public int type;
    public int id;
    public List<String> css;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
