package com.xyz.leeeyou.zhihuribao.data.model.ribao

data class Story(var images: List<String>,
                 var type: Int,
                 var id: Int,
                 var ga_prefix: String,
                 var title: String,
                 var date: String
)