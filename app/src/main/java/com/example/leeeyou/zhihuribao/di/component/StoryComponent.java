package com.example.leeeyou.zhihuribao.di.component;

import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.view.StoryActivity;
import com.example.leeeyou.zhihuribao.view.StoryDetailActivity;

import dagger.Component;

@Component(modules = StoryModule.class)
public interface StoryComponent {
    void inject(StoryActivity storyActivity);

    void inject(StoryDetailActivity storyDetailActivity);
}
