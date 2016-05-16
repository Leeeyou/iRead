package com.example.leeeyou.zhihuribao.data.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by leeeyou on 16/5/10.
 * <p/>
 * 日报列表的数据结构
 */
public class Story {
    public List<String> images;
    public int type;
    public int id;
    public String ga_prefix;
    public String title;
    public String date;//格式yyyy-MM-dd

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
