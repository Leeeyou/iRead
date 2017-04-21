package com.example.leeeyou.zhihuribao.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.adapter.StoryAdapter;
import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.Story;
import com.example.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.example.leeeyou.zhihuribao.di.component.StoryComponent;
import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.utils.T;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class StoryActivity extends Base_Original_Activity implements BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView mRecyclerView;

    @Inject
    Observable<RiBao> mStoryObservable;

    private StoryAdapter mStoryAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int TOTAL_COUNTER = 100;
    private static final int PAGE_SIZE = 0;

    public final List<Story> mStoryList = new ArrayList<>();
    public boolean isLoadMore = false;

    private View notLoadingView;

    private StoryModule mStoryModule;
    private StoryComponent mStoryComponent;

    private DateTime mDateTime;
    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        initDateTime();
        initUI();
        initAdapter();
        initSwipeRefreshLayout();
        getStories();
    }

    private void initDateTime() {
        mDateTime = DateTime.now().plusDays(1);
    }

    private void initUI() {
        setLeftTitleAndDoNotDisplayHomeAsUp(getResources().getString(R.string.app_name));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_zhihuribao);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshToFetchData();
            }
        });
    }

    private void onRefreshToFetchData() {
        isLoadMore = false;

//        mStoryAdapter.openLoadMore(PAGE_SIZE);
//        mStoryAdapter.removeAllFooterView();

        initDateTime();

        injectModule(getDayOfYear());

        getStories();
    }

    @NonNull
    private String getDayOfYear() {
        return String.valueOf(mDateTime.getYear()) +
                (mDateTime.getMonthOfYear() < 10 ? "0" + mDateTime.getMonthOfYear() : mDateTime.getMonthOfYear()) +
                mDateTime.getDayOfMonth();
    }

    private void initAdapter() {
        injectModule(getDayOfYear());

        mStoryAdapter = new StoryAdapter(R.layout.item_lv_story, mStoryList);
        mRecyclerView.setAdapter(mStoryAdapter);

//        initAdapterLoadMore();

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                Story story = mStoryList.get(position);
                startActivity(new Intent()
                        .setClass(StoryActivity.this, StoryDetailActivity.class)
                        .putExtra("storyId", story.getId())
                        .putExtra("storyTitle", story.getTitle()));
            }
        });
    }

    private void initAdapterLoadMore() {
        mStoryAdapter.openLoadAnimation();
        mStoryAdapter.openLoadMore(PAGE_SIZE);
        mStoryAdapter.setOnLoadMoreListener(this);
    }

    private void injectModule(String dateTime) {
        mStoryModule.date = dateTime;
        mStoryComponent.inject(this);
    }

    @Override
    void setupActivityComponent() {
        mStoryModule = new StoryModule();

        mStoryComponent = DaggerStoryComponent
                .builder()
                .storyModule(mStoryModule)
                .build();
    }

    private void getStories() {
        mStoryObservable
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mMaterialDialog = new MaterialDialog.Builder(StoryActivity.this)
                                .content("请等待...")
                                .progress(true, 0)
                                .show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<RiBao, Boolean>() {
                    @Override
                    public Boolean call(RiBao riBao) {
                        StringBuilder sb = new StringBuilder();
                        char[] chars = riBao.getDate().toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            if (i == 4 || i == 6) {
                                sb.append("-");
                            }
                            sb.append(chars[i]);
                        }

                        List<Story> stories = riBao.getStories();
                        for (Story story : stories) {
                            story.setDate(sb.toString());
                        }

                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RiBao>() {
                    @Override
                    public void onCompleted() {
                        mMaterialDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMaterialDialog.dismiss();

                        mSwipeRefreshLayout.setRefreshing(false);
                        e.printStackTrace();
                        T.showShort(StoryActivity.this, "出错了:" + e.getMessage());

                        if (isLoadMore) {
                            mStoryAdapter.showLoadMoreFailedView();
                        }
                    }

                    @Override
                    public void onNext(RiBao ribao) {
                        if (!isLoadMore) {
                            mStoryList.clear();
                        }

                        mStoryList.addAll(ribao.getStories());

                        if (mStoryList.size() >= TOTAL_COUNTER) {
                            mStoryAdapter.loadComplete();
                            if (notLoadingView == null) {
                                notLoadingView = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
                            }
                            mStoryAdapter.addFooterView(notLoadingView);
                        }

                        mStoryAdapter.setNewData(mStoryList);

                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;

        mDateTime = mDateTime.minusDays(1);
        injectModule(getDayOfYear());

//        mStoryAdapter.openLoadMore(PAGE_SIZE);
//        mStoryAdapter.removeAllFooterView();

        getStories();
    }

}
