package com.leeeyou.zhihudaily.view;

import android.os.Bundle;
import android.webkit.WebView;

import com.leeeyou.BaseActivity;
import com.leeeyou.R;
import com.leeeyou.util.HtmlUtils;
import com.leeeyou.zhihudaily.model.ZhiHuDailyRepositoryKt;
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyDetail;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class ZhiHuDailyDetailActivity extends BaseActivity {

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
        ZhiHuDailyRepositoryKt
                .fetchZhiHuDailyDetailById(storyId)
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
                .subscribe(new Subscriber<ZhiHuDailyDetail>() {
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
                    public void onNext(ZhiHuDailyDetail storyDetail) {
                        story_web.loadData(HtmlUtils.structHtml(storyDetail.getBody(), storyDetail.getCss()), "text/html; charset=UTF-8", null);
                    }
                });
    }

}
