package com.example.leeeyou.zhihuribao.adapter.one

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem

/**
 * Created by leeeyou on 2017/4/25.
 */
class MultipleItemQuickAdapterForOneIndex(data: List<OneIndexMultipleItem>) : BaseMultiItemQuickAdapter<OneIndexMultipleItem>(data) {

    init {
        addItemType(OneIndexMultipleItem.WEATHER, R.layout.item_one_index_weather)
        addItemType(OneIndexMultipleItem.TOP, R.layout.item_one_index_top)
        addItemType(OneIndexMultipleItem.READ, R.layout.item_one_index_read)
    }

    override fun convert(vh: BaseViewHolder, item: OneIndexMultipleItem) {
        when (item.itemType) {
            OneIndexMultipleItem.WEATHER -> {
                if (item.weather != null) {
                    vh.setText(R.id.tv_date, item.weather.date)
                }
            }
            OneIndexMultipleItem.TOP -> {
                if (item.indexData != null) {
                    vh.setText(R.id.tv_forward, item.indexData.forward)
                }
            }
            OneIndexMultipleItem.READ -> {

            }
        }
    }

}