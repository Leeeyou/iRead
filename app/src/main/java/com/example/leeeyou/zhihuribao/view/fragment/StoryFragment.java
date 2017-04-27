package com.example.leeeyou.zhihuribao.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.adapter.StoryAdapter;
import com.example.leeeyou.zhihuribao.data.model.ribao.RiBao;
import com.example.leeeyou.zhihuribao.data.model.ribao.Story;
import com.example.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.utils.T;
import com.example.leeeyou.zhihuribao.view.activity.IndexActivity;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class StoryFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private StoryAdapter mAdapter;

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
        updateData();
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
                        setAdapter(ribao.getStories());
                    }
                });
    }

    private void setAdapter(@NonNull List<Story> stories) {
        if (mAdapter == null) {
            mAdapter = new StoryAdapter(R.layout.item_lv_story, stories);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @NonNull
    private String getDayOfYear() {
        DateTime mDateTime = DateTime.now().plusDays(1);
        return String.valueOf(mDateTime.getYear()) +
                (mDateTime.getMonthOfYear() < 10 ? "0" + mDateTime.getMonthOfYear() : mDateTime.getMonthOfYear()) +
                mDateTime.getDayOfMonth();
    }

    @Override
    public boolean checkCanDoRefresh() {
        return !mRecyclerView.canScrollVertically(-1);
    }

    @Override
    public void updateData() {
        DaggerStoryComponent.builder().storyModule(new StoryModule(getDayOfYear())).build().inject(this);
        fetchStoryData();
    }
}
