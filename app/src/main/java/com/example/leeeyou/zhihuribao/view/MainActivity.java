package com.example.leeeyou.zhihuribao.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.data.model.RiBao;
import com.example.leeeyou.zhihuribao.data.model.Story;
import com.example.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.example.leeeyou.zhihuribao.di.module.StoryModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_zhihuribao)
    ListView lv_zhihuribao;

    @Inject
    Observable<RiBao> storyObservable;

    UniversalAdapter<Story> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RiBao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(RiBao ribao) {
                        setAdapter(ribao.stories);
                    }
                });
    }

    private void setAdapter(List<Story> stories) {
        if (mAdapter == null) {
            mAdapter = new UniversalAdapter<Story>(MainActivity.this, stories, R.layout.item_lv_story) {
                @Override
                public void convert(ViewHolder vh, Story story, int position) {
                    vh.setText(R.id.tv_story_title, story.title);
                    vh.setText(R.id.tv_story_type, String.valueOf(story.type));
                }
            };
            lv_zhihuribao.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


}
