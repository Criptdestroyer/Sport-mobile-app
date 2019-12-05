package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.response.DetailLeagueResponse
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.view.view.DetailLeagueView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguePresenter(
    private val view: DetailLeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLeagueDetail(idLeague: String?) {
        view.showLoading()
        doAsync {
            val dataLeague = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailLeague(idLeague)),
                DetailLeagueResponse::class.java
            )

            val dataPrevious = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPreviousMatch(idLeague)),
                MatchResponse::class.java
            )

            val dataNext = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNextMatch(idLeague)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailLeague(dataLeague, dataPrevious, dataNext)
            }
        }
    }
}