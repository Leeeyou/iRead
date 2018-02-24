package com.xyz.leeeyou.zhihuribao.data.model.one

import java.util.*

/**
 * Created by leeeyou on 2017/4/24.
 */
data class ID(var res: Int, var data: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ID

        if (res != other.res) return false
        if (!Arrays.equals(data, other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = res
        result = 31 * result + Arrays.hashCode(data)
        return result
    }
}