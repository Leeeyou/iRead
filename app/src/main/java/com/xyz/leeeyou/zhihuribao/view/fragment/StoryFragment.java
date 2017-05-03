package com.xyz.leeeyou.zhihuribao.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.adapter.StoryAdapter;
import com.xyz.leeeyou.zhihuribao.data.model.ribao.RiBao;
import com.xyz.leeeyou.zhihuribao.data.model.ribao.Story;
import com.xyz.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.xyz.leeeyou.zhihuribao.di.module.StoryModule;
import com.xyz.leeeyou.zhihuribao.utils.T;
import com.xyz.leeeyou.zhihuribao.view.activity.IndexActivity;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 知乎日报主界面
 */
public class StoryFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private StoryAdapter mAdapter;

    private String[] dateList = new String[7];
    private int mDatePosition = 0;
    private int mMostDate = 7;

    @Inject
    Observable<RiBao> storyObservable;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_story, container, false);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView_zhihuribao);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDateList();
        initAdapter();
        updateData();
    }

    private void initAdapter() {
        mAdapter = new StoryAdapter(R.layout.item_lv_story, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mDatePosition < mMostDate - 1) {
                    DaggerStoryComponent.builder().storyModule(new StoryModule(dateList[++mDatePosition])).build().inject(StoryFragment.this);
                    fetchStoryData();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        }, mRecyclerView);
    }

    private void fetchStoryData() {
        storyObservable.subscribeOn(Schedulers.newThread())
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
                        mAdapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ((IndexActivity) getActivity()).refreshComplete();
                        e.printStackTrace();
                        T.showShort(getActivity(), "出错了:" + e.getMessage());
                    }

                    @Override
                    public void onNext(RiBao ribao) {
                        ((IndexActivity) getActivity()).refreshComplete();
                        setDataToAdapter(ribao.getStories());
                    }
                });
    }

    private void setDataToAdapter(@NonNull List<Story> stories) {
        if (mDatePosition == 0) {
            mAdapter.setNewData(stories);
        } else {
            mAdapter.addData(stories);
        }
    }

    private void initDateList() {
        DateTime mDateTime = DateTime.now();
        for (int i = 0; i < mMostDate; i++) {
            DateTime tempDateTime = mDateTime.minusDays(i);
            dateList[i] = String.valueOf(tempDateTime.getYear()) +
                    (tempDateTime.getMonthOfYear() < 10 ? "0" + tempDateTime.getMonthOfYear() : tempDateTime.getMonthOfYear()) +
                    (tempDateTime.getDayOfMonth() < 10 ? "0" + tempDateTime.getDayOfMonth() : tempDateTime.getDayOfMonth());
        }
    }

    @Override
    public boolean checkCanDoRefresh() {
        return !mRecyclerView.canScrollVertically(-1);
    }

    @Override
    public void updateData() {
        mAdapter.removeAllFooterView();
        mDatePosition = 0;
        DaggerStoryComponent.builder().storyModule(new StoryModule(dateList[mDatePosition])).build().inject(this);
        fetchStoryData();
    }

}
