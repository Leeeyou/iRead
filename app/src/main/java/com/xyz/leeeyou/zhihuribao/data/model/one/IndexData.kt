package com.xyz.leeeyou.zhihuribao.data.model.one

/**
 * Created by leeeyou on 2017/4/24.
 */
data class IndexData(var id: String,
                     var date: String,
                     var weather: Weather,
                     var content_list: Array<OneIndex>)
