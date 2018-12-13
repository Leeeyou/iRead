package com.xyz.leeeyou.zhihuribao.vi.one

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.xyz.leeeyou.zhihuribao.data.model.one.OneIndex
import com.xyz.leeeyou.zhihuribao.data.model.one.Weather

/**
 * ClassName:   OneMultiItemEntity
 * Description: [ One ] module, multi-layout entity class
 *
 * Author:      leeeyou
 * Date:        2018/2/24 14:31
 */
class OneMultiItemEntity(private val itemType: Int,
                         val indexData: OneIndex? = null,
                         val weather: Weather? = null) : MultiItemEntity {
    companion object {
        const val BLANK = -1
        const val WEATHER = 0
        const val TOP = 1
        const val READ = 2
    }

    override fun getItemType(): Int = itemType

}