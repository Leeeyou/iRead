package com.leeeyou.wanandroid.model.bean

data class ResponseBanner(val data: List<Banner>,
                          val errorCode: Int = 0,
                          var errorMsg: String = "")