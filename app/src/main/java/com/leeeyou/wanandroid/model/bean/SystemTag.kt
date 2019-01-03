package com.leeeyou.wanandroid.model.bean

data class SystemTag(val children: List<SystemTag>,
                     val courseId: Int,
                     val id: Int,
                     val name: String,
                     val order: Int,
                     val parentChapterId: Int,
                     val userControlSetTop: Boolean,
                     val visible: Int)