package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.model.api.CoroutineContextProvider
import com.aemiralfath.league.model.response.SearchResponse
import com.aemiralfath.league.view.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun searchMatch(query: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val dataMatch = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.searchMatch(query)).await(),
                SearchResponse::class.java
            )

            view.showDetailMatch(dataMatch)
            view.hideLoading()
        }
    }
}