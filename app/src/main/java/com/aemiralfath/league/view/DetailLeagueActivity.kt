package com.aemiralfath.league.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aemiralfath.league.R
import com.aemiralfath.league.invisible
import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.DetailLeagueResponse
import com.aemiralfath.league.model.item.LeagueItem
import com.aemiralfath.league.presenter.DetailPresenter
import com.aemiralfath.league.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailLeagueActivity : AppCompatActivity(), DetailView {
    private lateinit var item: LeagueItem
    private lateinit var presenter: DetailPresenter
    private lateinit var leagueNameTextView: TextView
    private lateinit var leagueDescriptionTextView: TextView
    private lateinit var leagueImageView: ImageView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = intent.getParcelableExtra("EXTRA_ITEM")

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)
                        leagueImageView = imageView {
                            id = R.id.image_detail_league
                        }.lparams {
                            height = dip(250)
                            width = matchParent
                            bottomMargin = dip(24)
                        }


                        leagueNameTextView = textView {
                            id = R.id.name_detail_league
                            textSize = 25f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            height = wrapContent
                            width = matchParent
                            padding = dip(16)
                            bottom = R.id.image_detail_league
                        }

                        leagueDescriptionTextView = textView {
                            id = R.id.description_league
                            textSize = 15f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            height = wrapContent
                            width = matchParent
                            padding = dip(16)
                            bottom = R.id.name_detail_league
                        }
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }

        }

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getLeagueDetail(item.id)

        swipeRefresh.onRefresh {
            presenter.getLeagueDetail(item.id)
        }

    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailLeague(data: DetailLeagueResponse) {
        swipeRefresh.isRefreshing = false
        leagueNameTextView.text = data.leagues[0].LeagueName
        leagueDescriptionTextView.text = data.leagues[0].leagueDescription
        println(data.leagues[0].leagueBadge)
        data.leagues[0].leagueBadge?.let {
            Picasso.get().load(it).fit().into(leagueImageView)
        }
    }
}
