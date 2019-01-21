package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.manager.BaseActivity
import com.leeeyou.manager.MyLoadMoreView
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.HtmlUtils
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.wanandroid.model.bean.RecommendItem
import com.leeeyou.wanandroid.model.bean.RecommendList
import com.leeeyou.wanandroid.model.fetchProjectListByCategory
import kotlinx.android.synthetic.main.activity_similar_project.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SimilarProjectActivity : BaseActivity() {
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    var mPageIndex: Int = 0
    private lateinit var mSimilarProjectAdapter: BaseQuickAdapter<RecommendItem, BaseViewHolder>
    private var mPageCount: Int = 0
    private var mSelectedCategoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_similar_project)
        setLeftTitleAndDisplayHomeAsUp(intent.getStringExtra("category"))
        mSelectedCategoryId = intent.getIntExtra("categoryId", -1)
        initPtrFrame()
        initRecyclerView()
        fetchSimilarProjectListFromServer(mPageIndex)
    }

    private fun initPtrFrame() {
        initHeadView()
        ptrFrameSimilarProject.disableWhenHorizontalMove(true)
        ptrFrameSimilarProject.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return recyclerViewFirstItemCanVisible()
            }
        })
    }

    private fun recyclerViewFirstItemCanVisible() =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0

    private fun initHeadView() {
        val header = StoreHouseHeader(this)
        header.setTextColor(resources.getColor(R.color.colorTxtSelected))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString("Similar Project", 15)
        ptrFrameSimilarProject.headerView = header
        ptrFrameSimilarProject.addPtrUIHandler(header)
    }

    private fun pullDownToRefresh() {
        mPageIndex = 0
        fetchSimilarProjectListFromServer(mPageIndex)
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mSimilarProjectAdapter = object : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_project, null) {
            override fun convert(helper: BaseViewHolder?, item: RecommendItem?) {
                if (item == null || helper == null) return

                helper.setText(R.id.tv_title, HtmlUtils.translation(item.title))
                        .setText(R.id.tv_author, item.author)
                        .setText(R.id.tv_niceDate, item.niceDate)
                        .setGone(R.id.tv_view_similar_projects, false)
                        .addOnClickListener(R.id.rl_recommend_item)

                Glide.with(mContext).load(item.envelopePic).into(helper.getView(R.id.iv_project) as ImageView)
            }
        }
        mSimilarProjectAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.rl_recommend_item -> {
                    val recommendItem = adapter.getItem(position) as RecommendItem
                    startBrowserActivity(this@SimilarProjectActivity, recommendItem.link, recommendItem.title)
                }
            }
        }
        mSimilarProjectAdapter.setOnLoadMoreListener({
            if (mPageIndex + 1 == mPageCount) {
                mSimilarProjectAdapter.loadMoreEnd()
            } else {
                fetchSimilarProjectListFromServer(++mPageIndex)
            }
        }, recyclerViewSimilarProject)
        mSimilarProjectAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mSimilarProjectAdapter.setLoadMoreView(MyLoadMoreView())

        recyclerViewSimilarProject.layoutManager = mLinearLayoutManager
        recyclerViewSimilarProject.adapter = mSimilarProjectAdapter
    }

    private fun fetchSimilarProjectListFromServer(pageIndex: Int) {
        fetchProjectListByCategory(pageIndex, mSelectedCategoryId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<RecommendList>() {
                    override fun onSuccess(data: RecommendList?) {
                        if (data == null) return
                        renderSimilarProjectList(pageIndex, data)
                        if (pageIndex == 0) {
                            recyclerViewSimilarProject.smoothScrollToPosition(0)
                            if (data.datas.size < data.size) {
                                mSimilarProjectAdapter.loadMoreEnd()
                            }
                        } else if (pageIndex > 0) {
                            mSimilarProjectAdapter.loadMoreComplete()
                        }
                    }

                    override fun onCompleted() {
                        ptrFrameSimilarProject?.refreshComplete()
                        if (pageIndex > 0) {
                            mSimilarProjectAdapter.loadMoreComplete()
                        }
                    }
                })
    }

    private fun renderSimilarProjectList(witchPage: Int, data: RecommendList) {
        if (witchPage == 0) {
            mSimilarProjectAdapter.setNewData(data.datas)
        } else {
            mPageCount = data.pageCount
            mSimilarProjectAdapter.addData(data.datas)
        }
    }
}
