package com.example.leeeyou.zhihuribao.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.data.model.Story;

import java.util.List;

/**
 * Created by leeeyou on 16/9/27.
 */

public class StoryAdapter extends BaseQuickAdapter<Story> {

    public StoryAdapter(int layoutResId, List<Story> data) {
        super(layoutResId, data);
    }

    public StoryAdapter(List<Story> data) {
        super(data);
    }

    public StoryAdapter(View contentView, List<Story> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Story story) {

        baseViewHolder.setText(R.id.tv_story_title, story.title);

        baseViewHolder.setText(R.id.tv_story_time, story.date);

        Glide.with(mContext)
                .load(story.images.get(0))
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .into((ImageView) baseViewHolder.getView(R.id.iv_story_image));
    }
}
