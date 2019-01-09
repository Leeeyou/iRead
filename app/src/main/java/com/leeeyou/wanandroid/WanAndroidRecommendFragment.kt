package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.manager.BaseFragment
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.HtmlUtils
import com.leeeyou.util.inflate
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.wanandroid.model.bean.Banner
import com.leeeyou.wanandroid.model.bean.RecommendItem
import com.leeeyou.wanandroid.model.bean.RecommendList
import com.leeeyou.wanandroid.model.fetchBannerList
import com.leeeyou.wanandroid.model.fetchRecommendList
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_wan_android_recommend.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber


/**
 * ClassName:   WeatherFragment
 * Description: Play Android - Recommend
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class WanAndroidRecommendFragment : BaseFragment() {
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    var mPageIndex: Int = 0
    private lateinit var mRecommendAdapter: BaseQuickAdapter<RecommendItem, BaseViewHolder>
    private var mPageCount: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_recommend)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        fetchBannerListFromServer()
        fetchRecommendListFromServer(mPageIndex)
    }

    private fun initView() {
        initBanner()
        initPtrFrame()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecommendAdapter = object : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_recommend, null) {
            override fun convert(helper: BaseViewHolder?, item: RecommendItem?) {
                item?.takeIf { it.visible == 1 }?.also {
                    helper?.setText(R.id.tv_title, HtmlUtils.translation(it.title))
                            ?.setText(R.id.tv_author, "作者:" + it.author)
                            ?.setText(R.id.tv_category, "分类:" + it.superChapterName + " / " + it.chapterName)
                            ?.setText(R.id.tv_niceDate, it.niceDate)
                            ?.setGone(R.id.tv_refresh, it.fresh)
                }
            }
        }
        mRecommendAdapter.setOnLoadMoreListener({
            if (mPageIndex + 1 == mPageCount) {
                mRecommendAdapter.loadMoreEnd()
            } else {
                fetchRecommendListFromServer(++mPageIndex)
            }
        }, recyclerViewRecommend)
        mRecommendAdapter.setOnItemClickListener { adapter, _, position ->
            val item: RecommendItem = adapter.getItem(position) as RecommendItem
            startBrowserActivity(context!!, item.link, item.title)
        }
        mRecommendAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)

        recyclerViewRecommend.layoutManager = mLinearLayoutManager
        recyclerViewRecommend.adapter = mRecommendAdapter
    }

    private fun initBanner() {
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                context?.let {
                    imageView?.let {
                        Glide.with(context).load(path).into(imageView)
                    }
                }
            }
        })
        banner.setDelayTime(3000)
    }

    private fun initPtrFrame() {
        ptrFrameRecommend.disableWhenHorizontalMove(true)
        ptrFrameRecommend.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean = recyclerViewFirstItemCanVisible()
        })
    }

    private fun recyclerViewFirstItemCanVisible() =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() <= 0

    fun gotoFirstPage() {
        if (!recyclerViewFirstItemCanVisible()) pullDownToRefresh()
    }

    private fun pullDownToRefresh() {
        fetchBannerListFromServer()
        mPageIndex = 0
        fetchRecommendListFromServer(mPageIndex)
    }

    private fun fetchRecommendListFromServer(pageIndex: Int) {
        fetchRecommendList(pageIndex).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<RecommendList>() {
                    override fun onSuccess(t: RecommendList?) {
                        t?.let {
                            renderRecommendList(pageIndex, it)
                            if (mPageIndex == 0 && it.datas.size < it.size) {
                                mRecommendAdapter.loadMoreEnd()
                            } else if (mPageIndex > 0) {
                                mRecommendAdapter.loadMoreComplete()
                            }
                        }
                    }

                    override fun onCompleted() {
                        ptrFrameRecommend?.refreshComplete()
                    }
                })
    }

    private fun renderRecommendList(witchPage: Int, data: RecommendList) {
        if (witchPage == 0) {
            mRecommendAdapter.setNewData(data.datas)
        } else {
            mPageCount = data.pageCount
            mRecommendAdapter.addData(data.datas)
        }
    }

    private fun fetchBannerListFromServer() {
        fetchBannerList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<List<Banner>>() {
                    override fun onSuccess(t: List<Banner>?) {
                        t?.let { renderBanner(it) }
                    }
                })
    }

    private fun renderBanner(bannerList: List<Banner>) {
        bannerList.map { it.imagePath }.run {
            banner.setImages(this)
            banner.setBannerAnimation(Transformer.Default)
            banner.setOnBannerListener { it ->
                val banner = bannerList[it]
                Timber.d("click banner position is %s , the url is %s", it, banner.url)
                startBrowserActivity(context!!, banner.url, banner.title)
            }
            banner.start()
        }
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }

}
