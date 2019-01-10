package com.leeeyou

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Window
import android.widget.ImageView
import com.leeeyou.movie.MovieFragment
import com.leeeyou.wanandroid.WanAndroidFragment
import com.leeeyou.zhihudaily.view.ZhiHuDailyFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_index.*

private const val INDEX_VIEWPAGER_MOVIE_STR = "天气"
private const val INDEX_VIEWPAGER_ANDROID_STR = "安卓"
private const val INDEX_VIEWPAGER_ZHIHU_STR = "知乎"
const val APP_NAME = "RsKotlin"

/**
 * ClassName:   IndexActivity
 *
 * Author:      leeeyou
 * Date:        2018/12/21 18:02
 */
class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_index)
        initViewPager()
    }

    private fun initViewPager() {
        val pages = FragmentPagerItems(this)
        arrayOf(INDEX_VIEWPAGER_ANDROID_STR, INDEX_VIEWPAGER_MOVIE_STR, INDEX_VIEWPAGER_ZHIHU_STR)
                .forEach {
                    when (it) {
                        INDEX_VIEWPAGER_ANDROID_STR -> pages.add(FragmentPagerItem.of("", WanAndroidFragment::class.java))
                        INDEX_VIEWPAGER_MOVIE_STR -> pages.add(FragmentPagerItem.of("", MovieFragment::class.java))
                        INDEX_VIEWPAGER_ZHIHU_STR -> pages.add(FragmentPagerItem.of("", ZhiHuDailyFragment::class.java))
                    }
                }

        indexTabLayout.setCustomTabView { container, position, _ ->
            val icon = LayoutInflater.from(this@IndexActivity).inflate(R.layout.index_custom_tab_icon, container, false) as ImageView
            val res = this@IndexActivity.resources

            when (position) {
                0 -> icon.setImageDrawable(res.getDrawable(R.mipmap.index_icon_android))
                1 -> icon.setImageDrawable(res.getDrawable(R.mipmap.index_icon_douban))
                2 -> icon.setImageDrawable(res.getDrawable(R.mipmap.index_icon_zhihu))
                else -> throw IllegalStateException("Invalid position: $position")
            }
            icon
        }

        indexViewPager.offscreenPageLimit = 3
        indexViewPager.adapter = FragmentPagerItemAdapter(supportFragmentManager, pages)
        indexTabLayout.setViewPager(indexViewPager)
    }
}
