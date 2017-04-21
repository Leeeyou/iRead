package com.example.leeeyou.zhihuribao.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.data.model.Story1;

import java.util.List;

/**
 * Created by leeeyou on 16/9/27.
 */

public class StoryAdapter extends BaseQuickAdapter<Story1> {

    public StoryAdapter(int layoutResId, List<Story1> data) {
        super(layoutResId, data);
    }

    public StoryAdapter(List<Story1> data) {
        super(data);
    }

    public StoryAdapter(View contentView, List<Story1> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Story1 story) {

        baseViewHolder.setText(R.id.tv_story_title, story.getTitle());

        baseViewHolder.setText(R.id.tv_story_time, story.getDate());

        Glide.with(mContext)
                .load(story.getImages().get(0))
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .into((ImageView) baseViewHolder.getView(R.id.iv_story_image));
    }
}
