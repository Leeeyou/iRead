package com.xyz.leeeyou.zhihuribao.di.component

import com.xyz.leeeyou.zhihuribao.di.module.StoryModule
import com.xyz.leeeyou.zhihuribao.view.activity.StoryDetailActivity
import com.xyz.leeeyou.zhihuribao.view.fragment.StoryFragment
import dagger.Component
import javax.inject.Singleton

/**
 * ClassName:   StoryComponent
 * Description: [ Story ] module Component
 *
 * Author:      leeeyou
 * Date:        2018/2/24 15:15
 */
@Singleton
@Component(modules = [StoryModule::class])
interface StoryComponent {
    fun inject(storyDetailActivity: StoryDetailActivity)

    fun inject(storyFragment: StoryFragment)
}