package com.example.leeeyou.zhihuribao.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.data.model.Story;
import com.example.leeeyou.zhihuribao.view.activity.StoryDetailActivity;

import java.util.List;

/**
 * Created by leeeyou on 16/9/27.
 */

public class StoryAdapter extends BaseQuickAdapter<Story> {

    public StoryAdapter(int layoutResId, List<Story> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Story story) {
        baseViewHolder.setText(R.id.tv_story_title, story.getTitle());

        baseViewHolder.setText(R.id.tv_story_time, story.getDate());

        Glide.with(mContext)
                .load(story.getImages().get(0))
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .into((ImageView) baseViewHolder.getView(R.id.iv_story_image));

        baseViewHolder.setOnClickListener(R.id.rl_item_recommend, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, StoryDetailActivity.class)
                        .putExtra("storyId", story.getId())
                        .putExtra("storyTitle", story.getTitle()));
            }
        });
    }

}
