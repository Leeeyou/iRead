package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.util.inflate
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.wanandroid.model.bean.RecommendItem
import com.leeeyou.wanandroid.model.bean.ResponseSystemTag
import com.leeeyou.wanandroid.model.bean.SystemTag
import com.leeeyou.wanandroid.model.bean.SystemTagArticleList
import com.leeeyou.wanandroid.model.fetchSystemTagArticleList
import com.leeeyou.wanandroid.model.fetchSystemTagList
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.fragment_wan_android_system.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * ClassName:   WeatherFragment
 * Description: Play Android - System
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidSystemFragment : BaseFragment() {
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private var mPageIndex: Int = 0
    private lateinit var mSystemTagArticleAdapter: BaseQuickAdapter<RecommendItem, BaseViewHolder>
    private var mPageCount: Int = 0

    private var mSelectedParentTagPosition: Int = 0
    private var mSelectedChildTagPosition: Int = 0

    private var mSelectedParentTagId: Int = 0
    private var mSelectedChildTagId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_system)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        initPtrFrame()

        rl_system_tag_combine.setOnClickListener {
            val animation: RotateAnimation = if (sv_system_tag_all.visibility == View.VISIBLE) {
                RotateAnimation(90f, 0f, (iv_arrow_right.width / 2).toFloat(), (iv_arrow_right.height / 2).toFloat())
            } else {
                RotateAnimation(0f, 90f, (iv_arrow_right.width / 2).toFloat(), (iv_arrow_right.height / 2).toFloat())
            }
            animation.duration = 100
            animation.fillAfter = true
            animation.interpolator = AccelerateInterpolator()
            iv_arrow_right.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    if (sv_system_tag_all.visibility == View.VISIBLE) {
                        sv_system_tag_all.visibility = View.GONE
                    } else {
                        sv_system_tag_all.visibility = View.VISIBLE
                    }
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
        }

        fetchSystemTagList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    it.takeIf { response ->
                        response.errorCode >= 0
                    }?.also { response ->
                        renderSystemTag(response)
                    } ?: IllegalArgumentException("fetchSystemTagList接口返回异常")
                }.subscribe()
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mSystemTagArticleAdapter = object : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_recommend, null) {
            override fun convert(helper: BaseViewHolder?, item: RecommendItem?) {
                item?.takeIf { it.visible == 1 }?.also {
                    helper?.setText(R.id.tv_title, it.title)
                            ?.setText(R.id.tv_author, "作者:" + it.author)
                            ?.setText(R.id.tv_category, "分类:" + it.superChapterName + " / " + it.chapterName)
                            ?.setText(R.id.tv_niceDate, it.niceDate)
                            ?.setGone(R.id.tv_refresh, it.fresh)
                }
            }
        }
        mSystemTagArticleAdapter.setOnLoadMoreListener({
            if (mPageIndex + 1 == mPageCount) {
                mSystemTagArticleAdapter.loadMoreEnd()
            } else {
                fetchSystemTagArticleList(++mPageIndex)
            }
        }, recyclerViewSystem)
        mSystemTagArticleAdapter.setOnItemClickListener { adapter, _, position ->
            val item: RecommendItem = adapter.getItem(position) as RecommendItem
            startBrowserActivity(context!!, item.link, item.title)
        }
        mSystemTagArticleAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)

        recyclerViewSystem.layoutManager = mLinearLayoutManager
        recyclerViewSystem.adapter = mSystemTagArticleAdapter
    }

    private fun initPtrFrame() {
        ptrFrameSystemTag.disableWhenHorizontalMove(true)
        ptrFrameSystemTag.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean =
                    sv_system_tag_all.visibility != View.VISIBLE && recyclerViewFirstItemCanVisible()
        })
    }

    private fun pullDownToRefresh() {
        mPageIndex = 0
        fetchSystemTagArticleList(mPageIndex)
    }

    private fun recyclerViewFirstItemCanVisible() =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0

    private fun renderSystemTag(response: ResponseSystemTag) {
        mSelectedParentTagPosition = 0
        mSelectedChildTagPosition = 0

        val parentTagList = response.data
        val childTagList = parentTagList[mSelectedParentTagPosition].children

        mSelectedParentTagId = parentTagList[mSelectedParentTagPosition].id
        mSelectedChildTagId = childTagList[mSelectedChildTagPosition].id

        renderParentTag(parentTagList)
        renderChildTag(childTagList)
        processClickEvent(response)
    }

    private fun processClickEvent(response: ResponseSystemTag) {
        system_tag_parent.setOnTagClickListener { _, position, parent ->
            mSelectedParentTagPosition = position
            mSelectedChildTagPosition = 0
            mSelectedParentTagId = response.data[mSelectedParentTagPosition].id
            mSelectedChildTagId = response.data[mSelectedParentTagPosition].children[mSelectedChildTagPosition].id

            renderChildTag(response.data[mSelectedParentTagPosition].children)
            for (index in 0..parent.childCount) {
                parent.getChildAt(index)?.isClickable = mSelectedParentTagPosition != index
            }
            true
        }

        system_tag_child.setOnTagClickListener { _, position, parent ->
            mSelectedChildTagPosition = position
            mSelectedChildTagId = response.data[mSelectedParentTagPosition].children[mSelectedChildTagPosition].id
            for (index in 0..parent.childCount) {
                parent.getChildAt(index)?.isClickable = mSelectedChildTagPosition != index
            }
            updateSystemTagCombineShow()
            pullDownToRefresh()
            sv_system_tag_all.visibility = View.GONE
            true
        }
    }

    private fun fetchSystemTagArticleList(pageIndex: Int) {
        Timber.d("fetchSystemTagArticleList ,  parent id is %s , child id is %s", mSelectedParentTagId, mSelectedChildTagId)
        fetchSystemTagArticleList(pageIndex, mSelectedChildTagId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    ptrFrameSystemTag?.refreshComplete()

                    it.takeIf { response ->
                        response.errorCode >= 0
                    }?.also { response ->
                        Timber.d(it.data.toString())
                        renderSystemTagArticleList(pageIndex, response.data)

                        mSystemTagArticleAdapter.loadMoreComplete()
                        if (mPageIndex == 0 && response.data.datas.size <= response.data.size) {
                            mSystemTagArticleAdapter.loadMoreEnd()
                        }
                    } ?: IllegalArgumentException("fetchSystemTagArticleList接口返回异常")
                }
                .subscribe()
    }

    private fun renderSystemTagArticleList(pageIndex: Int, data: SystemTagArticleList) {
        if (pageIndex == 0) {
            mSystemTagArticleAdapter.setNewData(data.datas)
        } else {
            mPageCount = data.pageCount
            mSystemTagArticleAdapter.addData(data.datas)
        }
    }

    private fun renderParentTag(parentTagList: List<SystemTag>) {
        system_tag_parent.adapter = object : TagAdapter<SystemTag>(parentTagList) {
            override fun getView(parent: FlowLayout?, position: Int, systemTag: SystemTag?): View {
                systemTag?.let {
                    val parentTag = layoutInflater.inflate(R.layout.item_system_tag, null) as TextView
                    parentTag.text = it.name
                    return parentTag
                }
            }
        }
        system_tag_parent.adapter.setSelectedList(mSelectedParentTagPosition)
        system_tag_parent.getChildAt(mSelectedParentTagPosition)?.isClickable = false
    }

    private fun renderChildTag(childTagList: List<SystemTag>) {
        system_tag_child.adapter = object : TagAdapter<SystemTag>(childTagList) {
            override fun getView(parent: FlowLayout?, position: Int, systemTag: SystemTag?): View {
                systemTag?.let {
                    val childTag = layoutInflater.inflate(R.layout.item_system_tag, null) as TextView
                    childTag.text = it.name
                    return childTag
                }
            }
        }
        system_tag_child.adapter.setSelectedList(mSelectedChildTagPosition)
        system_tag_child.getChildAt(mSelectedChildTagPosition)?.isClickable = false

        updateSystemTagCombineShow()
        pullDownToRefresh()

        if (childTagList.size == 1) {
            sv_system_tag_all.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateSystemTagCombineShow() {
        val parentTag: SystemTag = system_tag_parent?.adapter?.getItem(mSelectedParentTagPosition) as SystemTag
        val childTag = system_tag_child?.adapter?.getItem(mSelectedChildTagPosition) as SystemTag
        tv_system_tag_combine.text = parentTag.name + " / " + childTag.name
    }

}
