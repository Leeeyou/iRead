package com.example.leeeyou.zhihuribao.adapter.one

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem

class MultipleItemQuickAdapter2(data: List<*>) : BaseMultiItemQuickAdapter<OneIndexMultipleItem>(data as MutableList<OneIndexMultipleItem>?) {

    init {
        addItemType(OneIndexMultipleItem.TOP, R.layout.item_one_index_top)
        addItemType(OneIndexMultipleItem.READ, R.layout.item_one_index_read)
    }

    override fun convert(baseViewHolder: BaseViewHolder, oneIndexMultipleItem: OneIndexMultipleItem) {

    }
}