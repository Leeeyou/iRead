package com.xyz.leeeyou.zhihuribao.di.component

import com.xyz.leeeyou.zhihuribao.di.module.StoryModule
import com.xyz.leeeyou.zhihuribao.view.activity.StoryDetailActivity
import com.xyz.leeeyou.zhihuribao.view.fragment.StoryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by leeeyou on 2017/4/24.
 */
@Singleton
@Component(modules = arrayOf(StoryModule::class))
interface StoryComponent {
    fun inject(storyDetailActivity: StoryDetailActivity)

    fun inject(storyFragment: StoryFragment)
}