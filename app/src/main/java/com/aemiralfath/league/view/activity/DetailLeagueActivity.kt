package com.aemiralfath.league.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.aemiralfath.league.R
import com.aemiralfath.league.invisible
import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.response.DetailLeagueResponse
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.item.LeagueItem
import com.aemiralfath.league.presenter.LeaguePresenter
import com.aemiralfath.league.view.view.DetailLeagueView
import com.aemiralfath.league.view.adapter.PagerAdapter
import com.aemiralfath.league.view.fragment.MatchFragment
import com.aemiralfath.league.visible
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_league_layout.*

class DetailLeagueActivity : AppCompatActivity(),
    DetailLeagueView {
    private var item: LeagueItem? = null
    private lateinit var leaguePresenter: LeaguePresenter
    private lateinit var leagueDescriptionTextView: TextView
    private lateinit var leagueImageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_league_layout)

        item = intent.getParcelableExtra("EXTRA_ITEM")

        supportActionBar?.title = item!!.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appbar.setExpanded(true)

        leagueDescriptionTextView = findViewById(R.id.description_league)
        leagueImageView = findViewById(R.id.image_detail_league)
        progressBar = findViewById(R.id.progressBarLeague)
        mTabLayout = findViewById(R.id.tabs)
        mViewPager = findViewById(R.id.view_pager)

        val request = ApiRepository()
        val gson = Gson()
        leaguePresenter = LeaguePresenter(this, request, gson)
        leaguePresenter.getLeagueDetail(item!!.id)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailLeague(
        dataLeague: DetailLeagueResponse,
        dataPrevious: MatchResponse,
        dataNext: MatchResponse
    ) {
        leagueDescriptionTextView.text = dataLeague.leagues[0].leagueDescription
        dataLeague.leagues[0].leagueBadge?.let {
            Picasso.get().load(it).fit().into(leagueImageView)
        }

        val adapter = PagerAdapter(supportFragmentManager)

        adapter.addFragment(MatchFragment(dataPrevious.events))
        adapter.addFragment(MatchFragment(dataNext.events))


        mViewPager.adapter = adapter
        mTabLayout.setupWithViewPager(mViewPager)
    }
}
