package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.T
import com.leeeyou.util.inflate
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.wanandroid.model.bean.RecommendItem
import com.leeeyou.wanandroid.model.bean.RecommendList
import com.leeeyou.wanandroid.model.bean.SystemTag
import com.leeeyou.wanandroid.model.fetchProjectCategory
import com.leeeyou.wanandroid.model.fetchProjectList
import com.leeeyou.wanandroid.model.fetchProjectListByCategory
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.fragment_wan_android_project.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * ClassName:   WeatherFragment
 * Description: Play Android - Project
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidProjectFragment : BaseFragment() {
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mProjectAdapter: BaseQuickAdapter<RecommendItem, BaseViewHolder>
    private var mPageCount: Int = 0
    private var mPageIndex: Int = 0

    private var mSelectedCategoryPosition: Int = 0
    private var mSelectedCategoryId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_project)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initPtrFrame()
        initProjectCategoryUI()
        fetchProjectCategoryFromServer()
    }

    private fun initProjectCategoryUI() {
        rl_project_category.setOnClickListener {
            if (sv_project_category.visibility == View.VISIBLE) hiddenDetailTagAnimation() else showDetailTagAnimation()
        }
    }

    private fun fetchProjectCategoryFromServer() {
        fetchProjectCategory().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<List<SystemTag>>() {
                    override fun onSuccess(t: List<SystemTag>?) {
                        t?.let {
                            renderProjectCategory(it)
                        }
                    }
                })
    }

    private fun renderProjectCategory(categoryList: List<SystemTag>) {
        mSelectedCategoryPosition = 0
        mSelectedCategoryId = categoryList[mSelectedCategoryPosition].id
        renderCategory(categoryList)
        processClickEvent(categoryList)
    }

    private fun processClickEvent(categoryList: List<SystemTag>) {
        flowLayout_project_category.setOnTagClickListener { _, position, parent ->
            mSelectedCategoryPosition = position
            mSelectedCategoryId = categoryList[mSelectedCategoryPosition].id

            for (index in 0..parent.childCount) {
                parent.getChildAt(index)?.isClickable = mSelectedCategoryPosition != index
            }

            updateCategoryShow()
            clickToRefreshList()
            hiddenDetailTagAnimation()

            true
        }
    }

    private fun clickToRefreshList() {
        mPageIndex = 0
        fetchProjectListByCategoryFromServer(mPageIndex)
    }

    private fun renderCategory(categoryList: List<SystemTag>) {
        flowLayout_project_category.adapter = object : TagAdapter<SystemTag>(categoryList) {
            override fun getView(parent: FlowLayout?, position: Int, systemTag: SystemTag?): View {
                val parentTag = layoutInflater.inflate(R.layout.item_system_tag, null) as TextView
                systemTag?.let {
                    parentTag.text = it.name
                    return parentTag
                }
                return parentTag
            }
        }
        flowLayout_project_category.adapter.setSelectedList(mSelectedCategoryPosition)
        flowLayout_project_category.getChildAt(mSelectedCategoryPosition)?.isClickable = false

        updateCategoryShow()
        pullDownToRefresh()
    }

    private fun updateCategoryShow() {
        val parentTag: SystemTag = flowLayout_project_category?.adapter?.getItem(mSelectedCategoryPosition) as SystemTag
        tv_project_category.text = parentTag.name
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mProjectAdapter = object : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_project, null) {
            override fun convert(helper: BaseViewHolder?, item: RecommendItem?) {
                item?.also {
                    helper?.also { helper ->
                        helper.setText(R.id.tv_title, it.title)
                                .setText(R.id.tv_author, it.author)
                                .setText(R.id.tv_niceDate, it.niceDate)

                        val imageView = helper.getView(R.id.iv_project) as ImageView
                        Glide.with(mContext).load(item.envelopePic).into(imageView)

                        helper.setOnClickListener(R.id.tv_view_similar_projects) {
                            T.showShort(mContext, "显示同类项目")
                        }
                    }
                }
            }
        }
        mProjectAdapter.setOnLoadMoreListener({
            if (mPageIndex + 1 == mPageCount) {
                mProjectAdapter.loadMoreEnd()
            } else {
                fetchProjectListFromServer(++mPageIndex)
            }
        }, recyclerViewProject)
        mProjectAdapter.disableLoadMoreIfNotFullPage()
        mProjectAdapter.setOnItemClickListener { adapter, _, position ->
            val item: RecommendItem = adapter.getItem(position) as RecommendItem
            startBrowserActivity(context!!, item.link, item.title)
        }
        mProjectAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)

        recyclerViewProject.layoutManager = mLinearLayoutManager
        recyclerViewProject.adapter = mProjectAdapter
    }

    private fun initPtrFrame() {
        ptrFrameProject.disableWhenHorizontalMove(true)
        ptrFrameProject.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean =
                    recyclerViewFirstItemCanVisible()
        })
    }

    private fun recyclerViewFirstItemCanVisible() =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0

    private fun pullDownToRefresh() {
        mPageIndex = 0
        fetchProjectListFromServer(mPageIndex)
    }

    private fun fetchProjectListFromServer(pageIndex: Int) {
        fetchProjectList(pageIndex).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<RecommendList>() {
                    override fun onSuccess(t: RecommendList?) {
                        t?.let {
                            renderProjectList(pageIndex, t)
                            if (mPageIndex == 0 && t.datas.size < t.size) {
                                mProjectAdapter.loadMoreEnd()
                            } else if (mPageIndex > 0) {
                                mProjectAdapter.loadMoreComplete()
                            }
                        }
                    }

                    override fun onCompleted() {
                        ptrFrameProject?.refreshComplete()
                        if (mPageIndex > 0) {
                            mProjectAdapter.loadMoreComplete()
                        }
                    }
                })
    }

    private fun fetchProjectListByCategoryFromServer(pageIndex: Int) {
        fetchProjectListByCategory(pageIndex, mSelectedCategoryId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<RecommendList>() {
                    override fun onSuccess(t: RecommendList?) {
                        t?.let {
                            renderProjectList(pageIndex, t)
                            if (pageIndex == 0 && t.datas.size < t.size) {
                                mProjectAdapter.loadMoreEnd()
                            } else if (pageIndex > 0) {
                                mProjectAdapter.loadMoreComplete()
                            }
                        }
                    }

                    override fun onCompleted() {
                        ptrFrameProject?.refreshComplete()
                        if (pageIndex > 0) {
                            mProjectAdapter.loadMoreComplete()
                        }
                    }
                })
    }

    private fun renderProjectList(pageIndex: Int, data: RecommendList) {
        if (pageIndex == 0) {
            mProjectAdapter.setNewData(ArrayList(data.datas))
        } else {
            mPageCount = data.pageCount
            mProjectAdapter.addData(data.datas)
        }
    }

    private fun showDetailTagAnimation() {
        val rotateAnimation = RotateAnimation(0f, 90f, (iv_arrow_right.width / 2).toFloat(), (iv_arrow_right.height / 2).toFloat())
        rotateAnimation.duration = 100
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = AccelerateInterpolator()
        iv_arrow_right.startAnimation(rotateAnimation)

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                sv_project_category.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
    }

    private fun hiddenDetailTagAnimation() {
        val rotateAnimation = RotateAnimation(90f, 0f, (iv_arrow_right.width / 2).toFloat(), (iv_arrow_right.height / 2).toFloat())
        rotateAnimation.duration = 100
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = AccelerateInterpolator()
        iv_arrow_right.startAnimation(rotateAnimation)

        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                sv_project_category.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
    }

}