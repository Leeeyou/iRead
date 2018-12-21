package com.leeeyou.zhihudaily.model.bean

data class ZhiHuDailyItem(var images: List<String>,
                          var type: Int,
                          var id: Int,
                          var ga_prefix: String,
                          var title: String,
                          var date: String
)