package com.xyz.leeeyou.zhihuribao.data.model.ribao

/**
 * Created by leeeyou on 2017/4/21.
 *
 * getLatestRiBao返回的数据结构
 *
 * 数据类是一种非常强大的类，它可以让你避免创建Java中的用于保存状态但又操作非常简单的POJO的模版代码。
 * 它们通常只提供了用于访问它们属性的简单的getter 和setter。
 */
data class RiBao(var date: String, var stories: List<Story>)