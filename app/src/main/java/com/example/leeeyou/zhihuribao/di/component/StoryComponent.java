package com.example.leeeyou.zhihuribao.di.component;

import com.example.leeeyou.zhihuribao.di.module.StoryModule;
import com.example.leeeyou.zhihuribao.view.MainActivity;

import dagger.Component;

@Component(modules = StoryModule.class)
public interface StoryComponent {
    void inject(MainActivity mainActivity);
}
