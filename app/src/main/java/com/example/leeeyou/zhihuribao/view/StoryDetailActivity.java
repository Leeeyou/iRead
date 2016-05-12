package com.example.leeeyou.zhihuribao.view;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.data.model.StoryDetail;
import com.example.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.example.leeeyou.zhihuribao.di.component.StoryComponent;
import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.utils.HtmlUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StoryDetailActivity extends Base_Original_Activity {

    private int storyId;

    @BindView(R.id.story_web)
    WebView story_web;

    @Inject
    Observable<StoryDetail> detailObservable;

    StoryComponent storyComponent;
    StoryModule storyModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        ButterKnife.bind(StoryDetailActivity.this);

        storyId = getIntent().getIntExtra("storyId", -1);
        String storyTitle = getIntent().getStringExtra("storyTitle");

        injectModule();

        setLeftTitleAndDoNotDisplayHomeAsUp(storyTitle);

        initWebView();

        getStoryDetail();
    }

    private void injectModule() {
        storyModule.storyId = storyId;
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
        detailObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StoryDetail storyDetail) {
//                        ViewUtils.setViewVisibility(commonLoading, false);
//                        ViewUtils.setViewVisibility(commonError, false);
                        story_web.loadData(HtmlUtils.structHtml(storyDetail.body, storyDetail.css), "text/html; charset=UTF-8", null);
                    }
                });
    }

}
