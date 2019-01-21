package com.leeeyou.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.leeeyou.R
import com.leeeyou.manager.BaseActivity
import com.leeeyou.movie.model.bean.DirectorAndCast
import com.leeeyou.movie.model.fetchMovieDetail
import com.leeeyou.util.startBrowserActivity
import com.leeeyou.widget.LoadingDialog
import kotlinx.android.synthetic.main.activity_movie.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MovieDetailActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setLeftTitleAndDisplayHomeAsUp("电影详情")


        val loadingDialog = LoadingDialog(this@MovieDetailActivity)
        loadingDialog.show()

        fetchMovieDetail(intent.getStringExtra("movieId")).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .doOnNext { movieDetail ->
                    loadingDialog.dismiss()

                    btn_share.setOnClickListener {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share")
                        intent.putExtra(Intent.EXTRA_TEXT, movieDetail.title + " : " + movieDetail.share_url)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(Intent.createChooser(intent, title))
                    }

                    Glide.with(this@MovieDetailActivity).load(movieDetail.images.large).into(iv_poster)
                    iv_poster.setOnClickListener {
                        startBrowserActivity(this@MovieDetailActivity, movieDetail.alt, movieDetail.title)
                    }
                    tv_movie_name.text = movieDetail.title
                    tv_movie_name_former.text = movieDetail.original_title
                    tv_movie_score.text = "评分：${movieDetail.rating.average}"

                    var types = ""
                    movieDetail.genres.forEach { typeName -> types += "$typeName," }
                    tv_movie_type.text = types.substring(0, types.length - 1)

                    tv_movie_time.text = "${movieDetail.year}年 大陆上映"

                    var countries = ""
                    movieDetail.countries.forEach { country -> countries += "$country " }
                    tv_movie_where.text = countries

                    iv_movie_summary.text = movieDetail.summary


                    val movieDetailAdapter = object : BaseQuickAdapter<DirectorAndCast, BaseViewHolder>(R.layout.item_movie_actor, null) {
                        override fun convert(helper: BaseViewHolder?, item: DirectorAndCast?) {
                            item?.also {
                                helper?.also {
                                    helper.setText(R.id.tv_movie_detail_actor_name, if (item.isDirector) "导演\n" + item.name else item.name)
                                            .addOnClickListener(R.id.iv_movie_detail_actor_avatar)
                                            .getView<ImageView>(R.id.iv_movie_detail_actor_avatar)
                                            ?.also { imageView -> Glide.with(this@MovieDetailActivity).load(item.avatar).into(imageView) }

                                }
                            }
                        }
                    }
                    movieDetailAdapter.setOnItemChildClickListener { adapter, view, position ->
                        when (view.id) {
                            R.id.iv_movie_detail_actor_avatar -> {
                                val directorAndCast = adapter.getItem(position) as DirectorAndCast
                                startBrowserActivity(this@MovieDetailActivity, directorAndCast.alt, directorAndCast.name)
                            }
                        }
                    }

                    val listOf = mutableListOf<DirectorAndCast>()
                    movieDetail.directors.forEach { director -> listOf.add(DirectorAndCast(director.alt, director.avatars.large, director.name, director.id, true)) }
                    movieDetail.casts.forEach { cast -> listOf.add(DirectorAndCast(cast.alt, cast.avatars.large, cast.name, cast.id, false)) }
                    movieDetailAdapter.setNewData(listOf)

                    rv_movie_actor.layoutManager = LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)
                    rv_movie_actor.adapter = movieDetailAdapter
                }
                .subscribe()
    }
}
