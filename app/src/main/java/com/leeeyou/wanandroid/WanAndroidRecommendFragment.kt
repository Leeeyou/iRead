package com.leeeyou.wanandroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.IndexActivity
import com.leeeyou.R
import com.leeeyou.manager.MyLoadMoreView
import com.leeeyou.service.entity.HttpResultEntity
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.HtmlUtils
import com.leeeyou.util.T
import com.leeeyou.util.inflate
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.wanandroid.model.bean.Banner
import com.leeeyou.wanandroid.model.bean.RecommendItem
import com.leeeyou.wanandroid.model.bean.RecommendList
import com.leeeyou.wanandroid.model.collectInsideArticle
import com.leeeyou.wanandroid.model.fetchBannerList
import com.leeeyou.wanandroid.model.fetchRecommendList
import com.leeeyou.wanandroid.model.unCollectInsideArticle
import com.leeeyou.widget.LoadingDialog
import com.leeeyou.widget.WishListIconView
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_wan_android_recommend.*
import rx.Observable
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
class WanAndroidRecommendFragment : WanAndroidBaseFragment() {
    private lateinit var mRecommendAdapter: BaseQuickAdapter<RecommendItem, BaseViewHolder>
    private var mFirstEnterLoadingDialog: LoadingDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_wan_android_recommend)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        pullDownToRefresh()
    }

    private fun initView() {
        mFirstEnterLoadingDialog = context?.let { LoadingDialog(it) }
        mFirstEnterLoadingDialog?.show()

        initBanner()
        initPtrFrame(ptrFrameRecommend, "Play Android")
        initRecyclerView()
    }

    override fun pullDownToRefresh() {
        super.pullDownToRefresh()
        fetchBannerListFromServer()
        fetchRecommendListFromServer(mPageIndex)
    }

    override fun checkRefresh(): Boolean {
        return recyclerViewFirstItemCanVisible()
                && (activity as IndexActivity).appBarLayoutVerticalOffset >= 0
    }

    private fun initRecyclerView() {
        context?.let { initLayoutManager(it) }
        mRecommendAdapter = object : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_recommend, null) {
            override fun convert(helper: BaseViewHolder?, item: RecommendItem?) {
                item?.takeIf { it.visible == 1 }?.also {
                    val wishListIconView = helper?.getView(R.id.wishListIcon) as WishListIconView
                    wishListIconView.isActivated = it.collect

                    helper.setText(R.id.tv_title, HtmlUtils.translation(it.title))
                            .setText(R.id.tv_author, "作者 : " + it.author)
                            .setText(R.id.tv_category, "分类 : " + it.superChapterName + " / " + it.chapterName)
                            .setText(R.id.tv_niceDate, it.niceDate)
                            .setGone(R.id.tv_refresh, it.fresh)
                            .addOnClickListener(R.id.wishListIcon)
                }
            }
        }
        mRecommendAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.wishListIcon -> {
                    var loadingDialog: LoadingDialog? = null
                    this@WanAndroidRecommendFragment.context?.also {
                        loadingDialog = LoadingDialog(it)
                        loadingDialog?.show()
                    }

                    val recommendItem = adapter.getItem(position) as RecommendItem
                    val observable: Observable<HttpResultEntity<String>> = if (view.isActivated) unCollectInsideArticle(recommendItem.id) else collectInsideArticle(recommendItem.id)
                    observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : DefaultHttpResultSubscriber<String>() {
                                override fun onSuccess(data: String?) {
                                    loadingDialog?.dismiss()
                                    recommendItem.collect = !view.isActivated
                                    adapter.data[position] = recommendItem
                                    adapter.notifyLoadMoreToLoading()
                                    (view as WishListIconView).toggleWishlisted()
                                }

                                override fun _onError(status: Int, msg: String?) {
                                    T.showShort(this@WanAndroidRecommendFragment.context, msg)
                                    loadingDialog?.dismiss()
                                }
                            })
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
        mRecommendAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        mRecommendAdapter.setLoadMoreView(MyLoadMoreView())

        recyclerViewRecommend.layoutManager = mLayoutManager
        recyclerViewRecommend.adapter = mRecommendAdapter
    }

    private fun initBanner() {
        banner.setDelayTime(3000).setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                if (context != null && imageView != null) {
                    Glide.with(context).load(path).into(imageView)
                }
            }
        })
    }

    fun gotoFirstPage() {
        if (!recyclerViewFirstItemCanVisible()) pullDownToRefresh()
    }

    private fun fetchRecommendListFromServer(pageIndex: Int) {
        fetchRecommendList(pageIndex).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<RecommendList>() {
                    override fun onSuccess(data: RecommendList?) {
                        data?.also {
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
                        mFirstEnterLoadingDialog?.dismiss()
                    }
                })
    }

    private fun renderRecommendList(witchPage: Int, recommendList: RecommendList) {
        if (witchPage == 0) {
            mRecommendAdapter.setNewData(recommendList.datas)
        } else {
            mPageCount = recommendList.pageCount
            mRecommendAdapter.addData(recommendList.datas)
        }
    }

    private fun fetchBannerListFromServer() {
        fetchBannerList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultHttpResultSubscriber<List<Banner>>() {
                    override fun onSuccess(data: List<Banner>?) {
                        data?.also { renderBanner(it) }
                    }
                })
    }

    private fun renderBanner(bannerList: List<Banner>) {
        bannerList.map { it.imagePath }.also {
            banner.setImages(it).setBannerAnimation(Transformer.Default).start()
                    .setOnBannerListener { position ->
                        val banner = bannerList[position]
                        startBrowserActivity(context!!, banner.url, banner.title)
                        Timber.d("click banner position is %s , the url is %s", position, banner.url)
                    }
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
