package com.leeeyou.zhihudaily.model.bean

/**
 * ClassName:    ZhiHuDaily
 * Description:  The data class is a very powerful class that
 *               lets you avoid creating boilerplate code in Java
 *               that holds POJOs that are state-controlled but have a very simple operation.
 *
 *               They usually only provide simple getters and setters for accessing their properties.
 *
 * Author:      leeeyou
 * Date:        2017/4/21 15:07
 */
data class ZhiHuDaily(var date: String, var stories: List<ZhiHuDailyItem>)