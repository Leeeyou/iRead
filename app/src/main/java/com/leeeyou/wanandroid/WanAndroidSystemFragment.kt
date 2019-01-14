package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
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
import com.leeeyou.manager.MyAnimationListener
import com.leeeyou.manager.MyLoadMoreView
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.HtmlUtils
import com.leeeyou.util.inflate
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.wanandroid.model.bean.RecommendItem
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
        initSystemTagUI()
        fetchSystemTagListFromServer()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser) {   // onPause
            hiddenDetailTagAnimation()
        }
    }

    private fun fetchSystemTagListFromServer() {
        fetchSystemTagList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<List<SystemTag>>() {
                    override fun onSuccess(t: List<SystemTag>?) {
                        t?.let {
                            renderSystemTag(it)
                        }
                    }
                })
    }

    private fun initSystemTagUI() {
        rl_system_tag_combine.setOnClickListener {
            if (sv_system_tag_all.visibility == View.VISIBLE) hiddenDetailTagAnimation() else showDetailTagAnimation()
        }
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mSystemTagArticleAdapter = object : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_recommend, null) {
            override fun convert(helper: BaseViewHolder?, item: RecommendItem?) {
                item?.also {
                    helper?.setText(R.id.tv_title, HtmlUtils.translation(it.title))
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
        mSystemTagArticleAdapter.disableLoadMoreIfNotFullPage()
        mSystemTagArticleAdapter.setOnItemClickListener { adapter, _, position ->
            val item: RecommendItem = adapter.getItem(position) as RecommendItem
            startBrowserActivity(context!!, item.link, item.title)
        }
        mSystemTagArticleAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mSystemTagArticleAdapter.setLoadMoreView(MyLoadMoreView())

        recyclerViewSystem.layoutManager = mLinearLayoutManager
        recyclerViewSystem.adapter = mSystemTagArticleAdapter
    }

    private fun initPtrFrame() {
        initHeadView()
        ptrFrameSystemTag.disableWhenHorizontalMove(true)
        ptrFrameSystemTag.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return sv_system_tag_all.visibility != View.VISIBLE
                        && recyclerViewFirstItemCanVisible()
                        && (activity as com.leeeyou.IndexActivity).appBarLayoutVerticalOffset >= 0
            }
        })
    }

    private fun initHeadView() {
        val header = StoreHouseHeader(context)
        header.setTextColor(resources.getColor(R.color.colorTxtEnable))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString("Play Android", 15)
        ptrFrameSystemTag.headerView = header
        ptrFrameSystemTag.addPtrUIHandler(header)
    }

    private fun pullDownToRefresh() {
        mPageIndex = 0
        fetchSystemTagArticleList(mPageIndex)
    }

    private fun recyclerViewFirstItemCanVisible() =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0

    private fun renderSystemTag(parentTagList: List<SystemTag>) {
        mSelectedParentTagPosition = 0
        mSelectedChildTagPosition = 0

        val childTagList = parentTagList[mSelectedParentTagPosition].children

        mSelectedParentTagId = parentTagList[mSelectedParentTagPosition].id
        mSelectedChildTagId = childTagList[mSelectedChildTagPosition].id

        renderParentTag(parentTagList)
        renderChildTag(childTagList)
        processClickEvent(parentTagList)
    }

    private fun processClickEvent(parentTagList: List<SystemTag>) {
        system_tag_parent.setOnTagClickListener { _, position, parent ->
            mSelectedParentTagPosition = position
            mSelectedChildTagPosition = 0
            mSelectedParentTagId = parentTagList[mSelectedParentTagPosition].id
            mSelectedChildTagId = parentTagList[mSelectedParentTagPosition].children[mSelectedChildTagPosition].id

            renderChildTag(parentTagList[mSelectedParentTagPosition].children)
            for (index in 0..parent.childCount) {
                parent.getChildAt(index)?.isClickable = mSelectedParentTagPosition != index
            }
            true
        }

        system_tag_child.setOnTagClickListener { _, position, parent ->
            mSelectedChildTagPosition = position
            mSelectedChildTagId = parentTagList[mSelectedParentTagPosition].children[mSelectedChildTagPosition].id
            for (index in 0..parent.childCount) {
                parent.getChildAt(index)?.isClickable = mSelectedChildTagPosition != index
            }
            updateSystemTagCombineShow()
            pullDownToRefresh()
            hiddenDetailTagAnimation()
            true
        }
    }

    private fun showDetailTagAnimation() {
        iv_arrow_right?.let {
            val rotateAnimation = RotateAnimation(0f, 90f, (iv_arrow_right.width / 2).toFloat(), (iv_arrow_right.height / 2).toFloat())
            rotateAnimation.duration = 100
            rotateAnimation.fillAfter = true
            rotateAnimation.interpolator = AccelerateInterpolator()
            iv_arrow_right.startAnimation(rotateAnimation)

            rotateAnimation.setAnimationListener(object : MyAnimationListener() {
                override fun onAnimationEnd(animation: Animation?) {
                    sv_system_tag_all?.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun hiddenDetailTagAnimation() {
        iv_arrow_right?.let {
            val rotateAnimation = RotateAnimation(90f, 0f, (iv_arrow_right.width / 2).toFloat(), (iv_arrow_right.height / 2).toFloat())
            rotateAnimation.duration = 100
            rotateAnimation.fillAfter = true
            rotateAnimation.interpolator = AccelerateInterpolator()
            iv_arrow_right.startAnimation(rotateAnimation)

            rotateAnimation.setAnimationListener(object : MyAnimationListener() {
                override fun onAnimationEnd(animation: Animation?) {
                    sv_system_tag_all.visibility = View.GONE
                }
            })
        }
    }

    private fun fetchSystemTagArticleList(pageIndex: Int) {
        Timber.d("fetchSystemTagArticleList ,  parent id is %s , child id is %s", mSelectedParentTagId, mSelectedChildTagId)
        fetchSystemTagArticleList(pageIndex, mSelectedChildTagId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<SystemTagArticleList>() {
                    override fun onSuccess(data: SystemTagArticleList?) {
                        data?.let {
                            renderSystemTagArticleList(pageIndex, data)
                            if (mPageIndex == 0 && data.datas.size < data.size) {
                                mSystemTagArticleAdapter.loadMoreEnd()
                            } else if (mPageIndex > 0) {
                                mSystemTagArticleAdapter.loadMoreComplete()
                            }
                        }
                    }

                    override fun onCompleted() {
                        ptrFrameSystemTag?.refreshComplete()
                        if (mPageIndex > 0) {
                            mSystemTagArticleAdapter.loadMoreComplete()
                        }
                    }
                })
    }

    private fun renderSystemTagArticleList(pageIndex: Int, data: SystemTagArticleList) {
        if (pageIndex == 0) {
            mSystemTagArticleAdapter.setNewData(ArrayList(data.datas))
        } else {
            mPageCount = data.pageCount
            mSystemTagArticleAdapter.addData(data.datas)
        }
    }

    private fun renderParentTag(parentTagList: List<SystemTag>) {
        system_tag_parent.adapter = object : TagAdapter<SystemTag>(parentTagList) {
            override fun getView(parent: FlowLayout?, position: Int, systemTag: SystemTag?): View {
                val parentTag = layoutInflater.inflate(R.layout.item_system_tag, null) as TextView
                systemTag?.let {
                    parentTag.text = HtmlUtils.translation(it.name)
                    return parentTag
                }
                return parentTag
            }
        }
        system_tag_parent.adapter.setSelectedList(mSelectedParentTagPosition)
        system_tag_parent.getChildAt(mSelectedParentTagPosition)?.isClickable = false
    }

    private fun renderChildTag(childTagList: List<SystemTag>) {
        system_tag_child.adapter = object : TagAdapter<SystemTag>(childTagList) {
            override fun getView(parent: FlowLayout?, position: Int, systemTag: SystemTag?): View {
                val childTag = layoutInflater.inflate(R.layout.item_system_tag, null) as TextView
                systemTag?.let {
                    childTag.text = HtmlUtils.translation(it.name)
                    return childTag
                }
                return childTag
            }
        }
        system_tag_child.adapter.setSelectedList(mSelectedChildTagPosition)
        system_tag_child.getChildAt(mSelectedChildTagPosition)?.isClickable = false

        updateSystemTagCombineShow()
        pullDownToRefresh()
        childTagList.size.takeIf { it == 1 }?.also { hiddenDetailTagAnimation() }
                ?: Timber.d("childTagList.size != 1")
    }

    @SuppressLint("SetTextI18n")
    private fun updateSystemTagCombineShow() {
        val parentTag: SystemTag = system_tag_parent?.adapter?.getItem(mSelectedParentTagPosition) as SystemTag
        val childTag = system_tag_child?.adapter?.getItem(mSelectedChildTagPosition) as SystemTag
        tv_system_tag_combine.text = parentTag.name + " / " + childTag.name
    }

}
