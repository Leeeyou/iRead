package com.xyz.leeeyou.zhihuribao.view.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.data.model.ribao.StoryDetail;
import com.xyz.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.xyz.leeeyou.zhihuribao.di.component.StoryComponent;
import com.xyz.leeeyou.zhihuribao.di.module.StoryModule;
import com.xyz.leeeyou.zhihuribao.utils.HtmlUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class StoryDetailActivity extends BaseOriginalActivity {

    private int storyId;

    WebView story_web;

    @Inject
    Observable<StoryDetail> detailObservable;

    private StoryComponent storyComponent;
    private StoryModule storyModule;

    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        story_web = (WebView) findViewById(R.id.story_web);

        storyId = getIntent().getIntExtra("storyId", -1);
        String storyTitle = getIntent().getStringExtra("storyTitle");

        injectModule();

        setLeftTitleAndDoNotDisplayHomeAsUp(storyTitle);

        initWebView();

        getStoryDetail();
    }

    private void injectModule() {
        storyModule.setStoryId(storyId);
        storyComponent.inject(this);
    }

    private void initWebView() {
        story_web.setVerticalScrollBarEnabled(false);
        story_web.getSettings().setDefaultTextEncodingName("UTF-8");
    }

    @Override
    void setupActivityComponent() {
        storyModule = new StoryModule();

        storyComponent = DaggerStoryComponent
                .builder()
                .storyModule(storyModule)
                .build();
    }

    public void getStoryDetail() {
        detailObservable
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mMaterialDialog = new MaterialDialog.Builder(StoryDetailActivity.this)
                                .content("请等待...")
                                .progress(true, 0)
                                .show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryDetail>() {
                    @Override
                    public void onCompleted() {
                        mMaterialDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMaterialDialog.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StoryDetail storyDetail) {
                        story_web.loadData(HtmlUtils.structHtml(storyDetail.getBody(), storyDetail.getCss()), "text/html; charset=UTF-8", null);
                    }
                });
    }

}
