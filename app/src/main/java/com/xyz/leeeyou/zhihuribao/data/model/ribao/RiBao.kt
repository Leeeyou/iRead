package com.xyz.leeeyou.zhihuribao.data.model.ribao

/**
 * ClassName:
 * Description:  The data class is a very powerful class that
 *               lets you avoid creating boilerplate code in Java
 *               that holds POJOs that are state-controlled but have a very simple operation.
 *
 *               They usually only provide simple getters and setters for accessing their properties.
 *
 * Author:      leeeyou
 * Date:        2017/4/21 15:07
 */
data class RiBao(var date: String, var stories: List<Story>)