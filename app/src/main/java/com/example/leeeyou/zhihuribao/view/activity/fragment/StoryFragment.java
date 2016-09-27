package com.example.leeeyou.zhihuribao.view.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.Story;
import com.example.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.utils.T;
import com.example.leeeyou.zhihuribao.view.activity.StoryDetailActivity;
import com.example.leeeyou.zhihuribao.view.manager.UniversalAdapter;
import com.example.leeeyou.zhihuribao.view.manager.ViewHolder;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class StoryFragment extends Fragment {

    @BindView(R.id.recyclerView_zhihuribao)
    ListView lv_zhihuribao;

    UniversalAdapter<Story> mAdapter;

    @Inject
    Observable<RiBao> storyObservable;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_story, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
//        TextView title = (TextView) view.findViewById(R.id.item_title);
//        title.setText(String.valueOf(position));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaggerStoryComponent
                .builder()
                .storyModule(new StoryModule())
                .build()
                .inject(this);

        getStories();
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
                        T.showShort(getActivity(), "出错了:" + e.getMessage());
                    }

                    @Override
                    public void onNext(RiBao ribao) {
                        setAdapter(ribao.stories);
                    }
                });
    }

    private void setAdapter(@NonNull List<Story> stories) {
        if (mAdapter == null) {
            mAdapter = new UniversalAdapter<Story>(getActivity(), stories, R.layout.item_lv_story) {
                @Override
                public void convert(ViewHolder vh, Story story, int position) {
                    vh.setText(R.id.tv_story_title, story.title);
                    vh.setText(R.id.tv_story_time, story.date);
                    vh.setImageByUrl(R.id.iv_story_image, story.images.get(0));
                }
            };
            lv_zhihuribao.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnItemClick(R.id.recyclerView_zhihuribao)
    public void onItemClick(int position) {
        Story story = (Story) lv_zhihuribao.getItemAtPosition(position);
        startActivity(new Intent()
                .setClass(getActivity(), StoryDetailActivity.class)
                .putExtra("storyId", story.id)
                .putExtra("storyTitle", story.title));
    }
}
