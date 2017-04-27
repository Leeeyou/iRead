package com.xyz.leeeyou.zhihuribao.di.component

import com.xyz.leeeyou.zhihuribao.di.module.OneModule
import com.xyz.leeeyou.zhihuribao.view.fragment.OneFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by leeeyou on 2017/4/24.
 */
@Singleton
@Component(modules = arrayOf(OneModule::class))
interface OneComponent {
    fun inject(oneFragment: OneFragment)
}