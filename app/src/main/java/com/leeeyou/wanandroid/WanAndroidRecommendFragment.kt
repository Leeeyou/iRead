package com.leeeyou.wanandroid

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
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
import com.leeeyou.IndexActivity
import com.leeeyou.R
import com.leeeyou.login.event.LoginSuccessEvent
import com.leeeyou.login.event.LogoutSuccessEvent
import com.leeeyou.manager.BaseFragment
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
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
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

        recyclerViewRecommend.layoutManager = mLinearLayoutManager
        recyclerViewRecommend.adapter = mRecommendAdapter
    }

    private fun initBanner() {
        banner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                context?.also {
                    imageView?.also {
                        Glide.with(context).load(path).into(imageView)
                    }
                }
            }
        })
        banner.setDelayTime(3000)
    }

    private fun initPtrFrame() {
        initHeadView()
        ptrFrameRecommend.disableWhenHorizontalMove(true)
        ptrFrameRecommend.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pullDownToRefresh()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return recyclerViewFirstItemCanVisible() && (activity as IndexActivity).appBarLayoutVerticalOffset >= 0
            }
        })
    }

    private fun initHeadView() {
        val header = StoreHouseHeader(context)
        header.setTextColor(resources.getColor(R.color.colorTxtEnable))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString("Play Android", 15)
        ptrFrameRecommend.headerView = header
        ptrFrameRecommend.addPtrUIHandler(header)
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
                    override fun onSuccess(data: List<Banner>?) {
                        data?.also { renderBanner(it) }
                    }
                })
    }

    private fun renderBanner(bannerList: List<Banner>) {
        bannerList.map { it.imagePath }.also {
            banner.setImages(it)
            banner.setBannerAnimation(Transformer.Default)
            banner.setOnBannerListener { position ->
                val banner = bannerList[position]
                Timber.d("click banner position is %s , the url is %s", position, banner.url)
                startBrowserActivity(context!!, banner.url, banner.title)
            }
            banner.start()
        }
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: LogoutSuccessEvent) {
        pullDownToRefresh()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: LoginSuccessEvent) {
        pullDownToRefresh()
    }
}
