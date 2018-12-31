package com.leeeyou.wanandroid.model.bean

data class RecommendList(val curPage: Int,
                         val datas: List<RecommendItem>,
                         val offset: Int,
                         val over: Boolean,
                         val pageCount: Int,
                         val size: Int,
                         val total: Int)