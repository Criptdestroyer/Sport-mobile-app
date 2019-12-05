package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.model.response.TeamResponse
import com.aemiralfath.league.view.view.DetailMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getMatchDetail(idEvent: String?){
        view.showLoading()
        doAsync {
            val dataMatch = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailMatch(idEvent)),
                MatchResponse::class.java
            )

            val dataTeamHome = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailTeam(dataMatch.events[0].homeTeam)),
                TeamResponse::class.java
            )

            val dataTeamAway = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailTeam(dataMatch.events[0].awayTeam)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailMatch(dataMatch, dataTeamHome, dataTeamAway)
            }
        }
    }
}