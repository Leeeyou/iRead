package com.example.leeeyou.zhihuribao.di.component

import com.example.leeeyou.zhihuribao.di.module.OneModule
import com.example.leeeyou.zhihuribao.view.fragment.OneFragment
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