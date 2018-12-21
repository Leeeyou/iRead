package com.leeeyou.zhihudaily.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leeeyou.BaseFragment;
import com.leeeyou.R;
import com.leeeyou.zhihudaily.model.ZhiHuDailyRepositoryKt;
import com.leeeyou.zhihudaily.model.bean.ZhiHuDaily;
import com.leeeyou.zhihudaily.model.bean.ZhiHuDailyItem;

import org.joda.time.DateTime;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * ClassName:   ZhiHuDailyFragment
 * Description: ZhiHuDailyFragment
 * <p>
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
public class ZhiHuDailyFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PtrClassicFrameLayout mPtrFrame;
    private ZhiHuDailyAdapter mAdapter;

    //when loading more, up to the date of the data can be loaded
    private String[] dateList = new String[7];
    private int mDatePosition = 0;
    private int mMostDate = 7;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_zhihu_daily, container, false);
        mRecyclerView = inflate.findViewById(R.id.recyclerViewRiBao);
        mPtrFrame = inflate.findViewById(R.id.ptrFrame);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDateList();
        initView();
        fetchZhiHuDailyList();
    }

    private void initView() {
        mPtrFrame.disableWhenHorizontalMove(true);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mDatePosition = 0;
                fetchZhiHuDailyList();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return !mRecyclerView.canScrollVertically(-1);
            }
        });

        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new ZhiHuDailyAdapter(R.layout.item_zhihu_daily, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mDatePosition < mMostDate - 1) {
                    fetchZhiHuDailyList();
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        }, mRecyclerView);
    }

    private void fetchZhiHuDailyList() {
        ZhiHuDailyRepositoryKt
                .fetchZhiHuDailyListByDate(dateList[++mDatePosition])
                .subscribeOn(Schedulers.newThread())
                .filter(new Func1<ZhiHuDaily, Boolean>() {
                    @Override
                    public Boolean call(ZhiHuDaily riBao) {
                        StringBuilder sb = new StringBuilder();
                        char[] chars = riBao.getDate().toCharArray();
                        for (int i = 0; i < chars.length; i++) {
                            if (i == 4 || i == 6) {
                                sb.append("-");
                            }
                            sb.append(chars[i]);
                        }

                        List<ZhiHuDailyItem> stories = riBao.getStories();
                        for (ZhiHuDailyItem item : stories) {
                            item.setDate(sb.toString());
                        }

                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHuDaily>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.loadMoreComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPtrFrame.refreshComplete();
                        e.printStackTrace();
                        com.leeeyou.util.T.showShort(getActivity(), "出错了:" + e.getMessage());
                    }

                    @Override
                    public void onNext(ZhiHuDaily zhiHuDaily) {
                        mPtrFrame.refreshComplete();
                        setDataToAdapter(zhiHuDaily.getStories());
                    }
                });
    }

    private void setDataToAdapter(@NonNull List<ZhiHuDailyItem> zhiHuDailyItemList) {
        if (mDatePosition == 0) {
            mAdapter.setNewData(zhiHuDailyItemList);
        } else {
            mAdapter.addData(zhiHuDailyItemList);
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
}
