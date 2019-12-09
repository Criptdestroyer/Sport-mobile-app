package com.aemiralfath.league.view.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.league.R
import com.aemiralfath.league.invisible
import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.item.LeagueItem
import com.aemiralfath.league.model.response.SearchResponse
import com.aemiralfath.league.presenter.SearchPresenter
import com.aemiralfath.league.view.adapter.LeagueAdapter
import com.aemiralfath.league.view.adapter.SearchAdapter
import com.aemiralfath.league.view.view.SearchMatchView
import com.aemiralfath.league.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(), SearchMatchView {
    private var items: MutableList<LeagueItem> = mutableListOf()
    private lateinit var presenter: SearchPresenter
    private lateinit var mSearchView: SearchView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

        verticalLayout {
            mSearchView = searchView {
                id = R.id.sv_match
                isClickable = true
                isFocusable = true
            }.lparams(matchParent, wrapContent)

            mRecyclerView = recyclerView {
                lparams(width = matchParent, height = matchParent)
                layoutManager = GridLayoutManager(context, 2)
                adapter = LeagueAdapter(items) {
                    startActivity<DetailLeagueActivity>("EXTRA_ITEM" to it)
                }
            }

            mProgressBar = progressBar {
            }.lparams(matchParent, matchParent)
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        mSearchView.queryHint = "Search Match?"

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return if (query.isBlank()) {
                    false
                } else {
                    presenter.searchMatch(query)
                    true
                }
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return if (newText.isBlank()) {
                    false
                } else {
                    presenter.searchMatch(newText)
                    true
                }
            }
        })

        mSearchView.setOnCloseListener {
            mRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
            mRecyclerView.adapter = LeagueAdapter(items) {
                startActivity<DetailLeagueActivity>("EXTRA_ITEM" to it)
            }
            true
        }

    }

    private fun initData() {
        val id = resources.getStringArray(R.array.league_id)
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_image)
        items.clear()

        for (i in id.indices) {
            items.add(
                LeagueItem(
                    id[i],
                    name[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        image.recycle()
    }

    override fun showDetailMatch(dataMatch: SearchResponse) {
        if (!dataMatch.event.isNullOrEmpty()) {
            mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
            mRecyclerView.adapter =
                SearchAdapter(dataMatch.event.filter { it.sportName == "Soccer" }) {
                    val intent = Intent(applicationContext, DetailMatchActivity::class.java)
                    intent.putExtra("EXTRA_ID", it.eventId)
                    intent.putExtra("EXTRA_NAME", it.eventName)
                    startActivity(intent)
                }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                startActivity<FavoriteActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        mProgressBar.visible()
    }

    override fun hideLoading() {
        mProgressBar.invisible()
    }

}
