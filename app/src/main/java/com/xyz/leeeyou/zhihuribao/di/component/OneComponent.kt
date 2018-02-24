package com.xyz.leeeyou.zhihuribao.di.component

import com.xyz.leeeyou.zhihuribao.di.module.OneModule
import com.xyz.leeeyou.zhihuribao.view.fragment.OneFragment
import dagger.Component
import javax.inject.Singleton

/**
 * ClassName:   OneComponent
 * Description: [ One ] module Component
 * 
 * Author:      leeeyou                             
 * Date:        2018/2/24 15:15                     
 */
@Singleton
@Component(modules = arrayOf(OneModule::class))
interface OneComponent {
    fun inject(oneFragment: OneFragment)
}