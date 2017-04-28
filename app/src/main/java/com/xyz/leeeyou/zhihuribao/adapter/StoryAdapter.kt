package com.xyz.leeeyou.zhihuribao.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.data.model.ribao.Story
import com.xyz.leeeyou.zhihuribao.view.activity.StoryDetailActivity

/**
 * Created by leeeyou on 16/9/27.
 */

class StoryAdapter(layoutResId: Int, data: List<Story>) : BaseQuickAdapter<Story, BaseViewHolder>(layoutResId, data) {

    override fun convert(viewHolder: BaseViewHolder, story: Story) {
        viewHolder
                .setText(R.id.tv_story_title, story.title)
                .setText(R.id.tv_story_time, story.date)
                .setOnClickListener(R.id.rl_item_recommend) {
                    mContext.startActivity(
                            Intent(mContext, StoryDetailActivity::class.java)
                                    .putExtra("storyId", story.id)
                                    .putExtra("storyTitle", story.title))
                }

        Glide.with(mContext).load(story.images[0]).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .into(viewHolder.getView<View>(R.id.iv_story_image) as ImageView)
    }

}
