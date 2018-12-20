package com.xyz.leeeyou.zhihuribao.view.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.data.model.ribao.StoryDetail;
import com.xyz.leeeyou.zhihuribao.di.module.StoryModule2Kt;
import com.xyz.leeeyou.zhihuribao.utils.HtmlUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class StoryDetailActivity extends BaseOriginalActivity {

    private int storyId;

    WebView story_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        story_web = findViewById(R.id.story_web);

        storyId = getIntent().getIntExtra("storyId", -1);
        String storyTitle = getIntent().getStringExtra("storyTitle");

        setLeftTitleAndDisplayHomeAsUp(storyTitle);
        initWebView();
        getStoryDetail();
    }

    private void initWebView() {
        story_web.setVerticalScrollBarEnabled(false);
        story_web.getSettings().setDefaultTextEncodingName("UTF-8");
    }


    public void getStoryDetail() {
        StoryModule2Kt
                .fetchStoryDetailById(storyId)
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
//                        mMaterialDialog = new MaterialDialog.Builder(StoryDetailActivity.this)
//                                .content("Loading ...")
//                                .progress(true, 0)
//                                .show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryDetail>() {
                    @Override
                    public void onCompleted() {
//                        mMaterialDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mMaterialDialog.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StoryDetail storyDetail) {
                        story_web.loadData(HtmlUtils.structHtml(storyDetail.getBody(), storyDetail.getCss()), "text/html; charset=UTF-8", null);
                    }
                });
    }

}
