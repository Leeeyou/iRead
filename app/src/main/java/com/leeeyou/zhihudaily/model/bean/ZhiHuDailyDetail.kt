package com.leeeyou.zhihudaily.model.bean

data class ZhiHuDailyDetail(var body: String,
                            var image_source: String,
                            var title: String,
                            var image: String,
                            var share_url: String,
                            var js: List<String>,
                            var ga_prefix: String,
                            var images: List<String>,
                            var type: Int,
                            var id: Int,
                            var css: List<String>
)