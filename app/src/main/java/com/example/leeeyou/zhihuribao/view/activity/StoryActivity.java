package com.example.leeeyou.zhihuribao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.adapter.StoryAdapter;
import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.Story;
import com.example.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.utils.T;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class StoryActivity extends Base_Original_Activity {

    RecyclerView recyclerView_zhihuribao;

    @Inject
    Observable<RiBao> storyObservable;

    StoryAdapter mStoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        recyclerView_zhihuribao = (RecyclerView) findViewById(R.id.recyclerView_zhihuribao);
        recyclerView_zhihuribao.setLayoutManager(new LinearLayoutManager(this));

        setLeftTitleAndDoNotDisplayHomeAsUp("知乎日报");

        getStories();
    }

    @Override
    void setupActivityComponent() {
        DaggerStoryComponent
                .builder()
                .storyModule(new StoryModule())
                .build()
                .inject(this);
    }

    private void getStories() {
        storyObservable.subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<RiBao, Boolean>() {
                    @Override
                    public Boolean call(RiBao riBao) {
                        StringBuilder sb = new StringBuilder();
                        char[] chars = riBao.date.toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            if (i == 4 || i == 6) {
                                sb.append("-");
                            }
                            sb.append(chars[i]);
                        }

                        List<Story> stories = riBao.stories;
                        for (Story story : stories) {
                            story.date = sb.toString();
                        }

                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RiBao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        T.showShort(StoryActivity.this, "出错了:" + e.getMessage());
                    }

                    @Override
                    public void onNext(RiBao ribao) {
                        setAdapter(ribao.stories);
                    }
                });
    }

    private void setAdapter(@NonNull final List<Story> stories) {

        if (mStoryAdapter == null) {
            mStoryAdapter = new StoryAdapter(R.layout.item_lv_story, stories);
            recyclerView_zhihuribao.setAdapter(mStoryAdapter);

            recyclerView_zhihuribao.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                    Story story = stories.get(position);
                    startActivity(new Intent()
                            .setClass(StoryActivity.this, StoryDetailActivity.class)
                            .putExtra("storyId", story.id)
                            .putExtra("storyTitle", story.title));
                }
            });
        } else {
            mStoryAdapter.notifyDataSetChanged();
        }
    }

}
