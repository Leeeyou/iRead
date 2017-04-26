package com.example.leeeyou.zhihuribao.adapter.one

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.leeeyou.zhihuribao.R
import com.example.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem

/**
 * Created by leeeyou on 2017/4/25.
 */
class MultipleItemQuickAdapterForOneIndex(data: List<OneIndexMultipleItem>) : BaseMultiItemQuickAdapter<OneIndexMultipleItem>(data) {

    init {
        addItemType(OneIndexMultipleItem.BLANK, R.layout.item_one_index_blank)
        addItemType(OneIndexMultipleItem.WEATHER, R.layout.item_one_index_weather)
        addItemType(OneIndexMultipleItem.TOP, R.layout.item_one_index_top)
        addItemType(OneIndexMultipleItem.READ, R.layout.item_one_index_read)
    }

    override fun convert(vh: BaseViewHolder, item: OneIndexMultipleItem) {
        when (item.itemType) {
            OneIndexMultipleItem.WEATHER -> {
                if (item.weather != null) {
                    vh.setText(R.id.tv_date, item.weather.date)
                    vh.setText(R.id.tv_climate, item.weather.climate + "，" + item.weather.city_name)
                }
            }
            OneIndexMultipleItem.TOP -> {
                if (item.indexData != null) {
                    Glide.with(mContext).load(item.indexData.img_url).into(vh.getView(R.id.img))
                    vh.setText(R.id.tv_author, item.indexData.title + " | " + item.indexData.pic_info)
                    vh.setText(R.id.tv_forward, item.indexData.forward)
                    vh.setText(R.id.tv_words_info, item.indexData.words_info)
                    vh.setText(R.id.tv_like, item.indexData.like_count.toString())
                }
            }
            OneIndexMultipleItem.READ -> {
                if (item.indexData != null) {
                    Glide.with(mContext).load(item.indexData.img_url).into(vh.getView(R.id.img))
                    vh.setText(R.id.tv_title, item.indexData.title)
                    vh.setText(R.id.tv_author_name, "文/" + item.indexData.author.user_name)
                    vh.setText(R.id.tv_forward, item.indexData.forward)
                    vh.setText(R.id.tv_like, item.indexData.like_count.toString())
                }
            }
        }
    }

}