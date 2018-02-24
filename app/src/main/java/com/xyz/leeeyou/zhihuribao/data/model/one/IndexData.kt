package com.xyz.leeeyou.zhihuribao.data.model.one

import java.util.*

data class IndexData(var id: String,
                     var date: String,
                     var weather: Weather,
                     var content_list: Array<OneIndex>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IndexData

        if (id != other.id) return false
        if (date != other.date) return false
        if (weather != other.weather) return false
        if (!Arrays.equals(content_list, other.content_list)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + weather.hashCode()
        result = 31 * result + Arrays.hashCode(content_list)
        return result
    }
}
