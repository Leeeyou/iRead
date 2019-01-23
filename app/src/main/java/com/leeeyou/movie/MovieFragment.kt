package com.leeeyou.movie

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import `in`.srain.cube.views.ptr.header.StoreHouseHeader
import `in`.srain.cube.views.ptr.util.PtrLocalDisplay.dp2px
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
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
import com.leeeyou.manager.BaseFragment
import com.leeeyou.movie.model.bean.ResponseHotMovie
import com.leeeyou.movie.model.fetchHotMovieList
import com.leeeyou.util.HtmlUtils
import com.leeeyou.util.inflate
import kotlinx.android.synthetic.main.fragment_movie.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * ClassName:   MovieFragment
 * Description: Movie
 *
 * Author:      leeeyou
 * Date:        2017/4/24 13:46
 */
class MovieFragment : BaseFragment() {
    private lateinit var mGridLayoutManager: LinearLayoutManager
    private lateinit var mHotMovieAdapter: BaseQuickAdapter<ResponseHotMovie.SubjectsBean, BaseViewHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_movie)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initPtrFrame()
        fetchHotMovieListFromServer()
    }

    private fun fetchHotMovieListFromServer() {
        fetchHotMovieList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .doOnNext { it ->
                    it?.also {
                        it.subjects?.also { subjectList ->
                            mHotMovieAdapter.setNewData(ArrayList(subjectList))
                            recyclerViewMovie?.smoothScrollToPosition(0)
                        }
                    }
                }.doOnCompleted {
                    ptrFrameHotMovie?.refreshComplete()
                }.doOnError { Timber.e(it, "fetchHotMovieList&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&") }.subscribe()
    }

    private fun initPtrFrame() {
        initHeadView()
        ptrFrameHotMovie.disableWhenHorizontalMove(true)
        ptrFrameHotMovie.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                fetchHotMovieListFromServer()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return !recyclerViewMovie.canScrollVertically(-1) && (activity as IndexActivity).appBarLayoutVerticalOffset >= 0
            }
        })
    }

    private fun initHeadView() {
        val header = StoreHouseHeader(context)
        header.setTextColor(resources.getColor(R.color.colorTxtSelected))
        header.setPadding(0, dp2px(15f), 0, 0)
        header.initWithString("Being Hot", 15)
        ptrFrameHotMovie.headerView = header
        ptrFrameHotMovie.addPtrUIHandler(header)
    }

    private fun initRecyclerView() {
        mGridLayoutManager = GridLayoutManager(context, 2)
        mHotMovieAdapter = object : BaseQuickAdapter<ResponseHotMovie.SubjectsBean, BaseViewHolder>(R.layout.item_hot_movie, null) {
            override fun convert(helper: BaseViewHolder?, item: ResponseHotMovie.SubjectsBean?) {
                item?.also {
                    helper?.also { helper ->
                        helper.setText(R.id.tv_title, HtmlUtils.translation(it.title))
                                .setText(R.id.tv_score, "评分：" + it.rating.average)
                        val imageView = helper.getView(R.id.iv_hot_movie) as ImageView
                        Glide.with(mContext).load(it.images.large).into(imageView)

                        helper.setOnClickListener(R.id.iv_hot_movie) {
                            val intent = Intent(this@MovieFragment.activity, MovieDetailActivity::class.java)
                            intent.putExtra("movieId", item.id)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
        mHotMovieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        recyclerViewMovie.layoutManager = mGridLayoutManager
        recyclerViewMovie.adapter = mHotMovieAdapter
    }

}