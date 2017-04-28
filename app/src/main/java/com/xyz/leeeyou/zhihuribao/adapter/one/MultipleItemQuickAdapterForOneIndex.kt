package com.xyz.leeeyou.zhihuribao.adapter.one

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem

/**
 * Created by leeeyou on 2017/4/25.
 */
class MultipleItemQuickAdapterForOneIndex(data: MutableList<OneIndexMultipleItem>?) : BaseMultiItemQuickAdapter<OneIndexMultipleItem, BaseViewHolder>(data) {

    init {
        addItemType(OneIndexMultipleItem.BLANK, R.layout.item_one_index_blank)
        addItemType(OneIndexMultipleItem.WEATHER, R.layout.item_one_index_weather)
        addItemType(OneIndexMultipleItem.TOP, R.layout.item_one_index_top)
        addItemType(OneIndexMultipleItem.READ, R.layout.item_one_index_read)
    }

    override fun convert(vh: BaseViewHolder, item: OneIndexMultipleItem) {
        when (item.itemType) {
            OneIndexMultipleItem.WEATHER -> {
                vh.setText(R.id.tv_date, item.weather?.date)
                        .setText(R.id.tv_climate, item.weather?.climate + "，" + item.weather?.city_name)
            }
            OneIndexMultipleItem.TOP -> {
                Glide.with(mContext).load(item.indexData?.img_url).into(vh.getView(R.id.img))
                vh.setText(R.id.tv_author, item.indexData?.title + " | " + item.indexData?.pic_info)
                        .setText(R.id.tv_forward, item.indexData?.forward)
                        .setText(R.id.tv_words_info, item.indexData?.words_info)
                        .setText(R.id.tv_like, item.indexData?.like_count.toString() + "个赞")
                        .setOnClickListener(R.id.tv_share) {}
                        .setOnClickListener(R.id.rl_item_one_index_top) {}
            }
            OneIndexMultipleItem.READ -> {
                Glide.with(mContext).load(item.indexData?.img_url).into(vh.getView(R.id.img))
                vh.setText(R.id.tv_title, item.indexData?.title)
                        .setText(R.id.tv_author_name, "文/" + item.indexData?.author?.user_name)
                        .setText(R.id.tv_forward, item.indexData?.forward)
                        .setText(R.id.tv_like, item.indexData?.like_count.toString() + "个赞")
                        .setOnClickListener(R.id.tv_share) {}
                        .setOnClickListener(R.id.rl_item_one_index_read) {}
            }
        }
    }

}
