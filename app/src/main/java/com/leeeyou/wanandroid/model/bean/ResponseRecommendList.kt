package com.leeeyou.wanandroid.model.bean

data class ResponseRecommendList(val data: RecommendList,
                                 val errorCode: Int = 0,
                                 var errorMsg: String = "")