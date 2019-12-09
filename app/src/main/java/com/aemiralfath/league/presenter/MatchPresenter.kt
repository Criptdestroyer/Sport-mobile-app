package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.model.api.CoroutineContextProvider
import com.aemiralfath.league.model.response.TeamResponse
import com.aemiralfath.league.view.view.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchDetail(idEvent: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val dataMatch = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getDetailMatch(idEvent)).await(),
                MatchResponse::class.java
            )

            val dataTeamHome = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getDetailTeam(dataMatch.events[0].homeTeam)).await(),
                TeamResponse::class.java
            )

            val dataTeamAway = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getDetailTeam(dataMatch.events[0].awayTeam)).await(),
                TeamResponse::class.java
            )


            view.showDetailMatch(dataMatch, dataTeamHome, dataTeamAway)
            view.hideLoading()
        }
    }
}