package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.model.response.SearchResponse
import com.aemiralfath.league.view.view.SearchMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun searchMatch(query: String?) {
        view.showLoading()
        doAsync {
            val dataMatch = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.searchMatch(query)),
                SearchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailMatch(dataMatch)
            }
        }
    }
}