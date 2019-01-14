package com.leeeyou

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.leeeyou.login.LoginActivity
import com.leeeyou.movie.MovieFragment
import com.leeeyou.opensource.OpenSourceActivity
import com.leeeyou.setting.SettingActivity
import com.leeeyou.skin.ChangeSkinActivity
import com.leeeyou.wanandroid.WanAndroidFragment
import com.leeeyou.zhihudaily.view.ZhiHuDailyFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_index.*


private const val INDEX_VIEWPAGER_MOVIE_STR = "电影"
private const val INDEX_VIEWPAGER_ANDROID_STR = "安卓"
private const val INDEX_VIEWPAGER_ZHIHU_STR = "知乎"

/**
 * ClassName:   IndexActivity
 *
 * Author:      leeeyou
 * Date:        2018/12/21 18:02
 */
class IndexActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var appBarLayoutVerticalOffset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        initDrawLayout()
        initViewPager()
    }

    private fun initDrawLayout() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            appBarLayoutVerticalOffset = verticalOffset
        })

        navigation.setNavigationItemSelectedListener(this)

        navigation.getHeaderView(0).findViewById<Button>(R.id.btn_goto_login).setOnClickListener {
            startActivity(Intent(this@IndexActivity, LoginActivity::class.java))
            drawer_layout.closeDrawer(GravityCompat.START)
        }
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

//        indexViewPager.offscreenPageLimit = 3
        indexViewPager.adapter = FragmentPagerItemAdapter(supportFragmentManager, pages)
        indexTabLayout.setViewPager(indexViewPager)
    }

    override fun onNavigationItemSelected(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.nav_skin -> {
                startActivity(Intent(this@IndexActivity, ChangeSkinActivity::class.java))
            }
            R.id.nav_open_source -> {
                startActivity(Intent(this@IndexActivity, OpenSourceActivity::class.java))
            }
            R.id.nav_setting -> {
                startActivity(Intent(this@IndexActivity, SettingActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
