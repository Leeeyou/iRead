package com.leeeyou.zhihudaily.view

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyItem

/**
 * ClassName:   ZhiHuDailyAdapter
 * Author:      leeeyou
 * Date:        2016/9/27 14:45
 */
class ZhiHuDailyAdapter(layoutResId: Int, data: List<ZhiHuDailyItem>?)
    : BaseQuickAdapter<ZhiHuDailyItem, BaseViewHolder>(layoutResId, data) {

    override fun convert(vh: BaseViewHolder, story: ZhiHuDailyItem?) {
        if (story == null) return

        vh.setText(R.id.tv_story_title, story.title)
                .setText(R.id.tv_story_time, story.date)
                .addOnClickListener(R.id.rl_item_recommend)
        Glide.with(mContext).load(story.images[0]).into(vh.getView(R.id.iv_story_image))
    }

}
