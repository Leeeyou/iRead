package com.example.leeeyou.zhihuribao.data.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by leeeyou on 16/5/10.
 * <p/>
 * getLatestRiBao返回的数据结构
 */
public class RiBao {
    public String date;
    public List<Story2> stories;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
