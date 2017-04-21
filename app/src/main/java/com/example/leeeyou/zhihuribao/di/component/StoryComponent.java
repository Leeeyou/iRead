package com.example.leeeyou.zhihuribao.di.component;

import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.view.activity.StoryActivity;
import com.example.leeeyou.zhihuribao.view.activity.StoryDetailActivity;
import com.example.leeeyou.zhihuribao.view.activity.fragment.StoryFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = StoryModule.class)
public interface StoryComponent {
    void inject(StoryActivity storyActivity);

    void inject(StoryDetailActivity storyDetailActivity);

    void inject(StoryFragment storyFragment);
}
