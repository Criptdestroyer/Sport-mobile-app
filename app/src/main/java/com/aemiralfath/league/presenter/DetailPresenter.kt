package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.api.DetailLeagueResponse
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.view.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLeagueDetail(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailLeague(idLeague)),
                DetailLeagueResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showDetailLeague(data)
            }
        }
    }
}