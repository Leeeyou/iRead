package com.example.leeeyou.zhihuribao.data.model

/**
 * Created by leeeyou on 2017/4/21.
 */
data class Story(var images: List<String>,
                 var type: Int,
                 var id: Int,
                 var ga_prefix: String,
                 var title: String,
                 var date: String
)