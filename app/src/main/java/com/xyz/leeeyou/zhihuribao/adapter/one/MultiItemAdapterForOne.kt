package com.xyz.leeeyou.zhihuribao.adapter.one

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.vi.one.OneMultiItemEntity

/**
 * ClassName:   MultiItemAdapterForOne
 * Description: [ One ] module, multi-layout adapter
 * 
 * Author:      leeeyou                             
 * Date:        2018/2/24 14:33                     
 */
class MultiItemAdapterForOne(data: MutableList<OneMultiItemEntity>?) : BaseMultiItemQuickAdapter<OneMultiItemEntity, BaseViewHolder>(data) {

    init {
        addItemType(OneMultiItemEntity.BLANK, R.layout.item_one_index_blank)
        addItemType(OneMultiItemEntity.WEATHER, R.layout.item_one_index_weather)
        addItemType(OneMultiItemEntity.TOP, R.layout.item_one_index_top)
        addItemType(OneMultiItemEntity.READ, R.layout.item_one_index_read)
    }

    override fun convert(vh: BaseViewHolder, item: OneMultiItemEntity) {
        when (item.itemType) {
            OneMultiItemEntity.WEATHER -> {
                vh.setText(R.id.tv_date, item.weather?.date)
                        .setText(R.id.tv_climate, item.weather?.climate + "，" + item.weather?.city_name)
            }
            OneMultiItemEntity.TOP -> {
                Glide.with(mContext).load(item.indexData?.img_url).into(vh.getView(R.id.img))
                vh.setText(R.id.tv_author, item.indexData?.title + " | " + item.indexData?.pic_info)
                        .setText(R.id.tv_forward, item.indexData?.forward)
                        .setText(R.id.tv_words_info, item.indexData?.words_info)
                        .setText(R.id.tv_like, item.indexData?.like_count.toString() + "个赞")
                        .setOnClickListener(R.id.tv_share) {}
                        .setOnClickListener(R.id.rl_item_one_index_top) {}
            }
            OneMultiItemEntity.READ -> {
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
