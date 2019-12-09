package com.aemiralfath.league.presenter

import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.response.DetailLeagueResponse
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.api.TheSportDBApi
import com.aemiralfath.league.model.api.CoroutineContextProvider
import com.aemiralfath.league.view.view.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaguePresenter(
    private val view: DetailLeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueDetail(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val dataLeague = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getDetailLeague(idLeague)).await(),
                DetailLeagueResponse::class.java
            )

            val dataPrevious = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getPreviousMatch(idLeague)).await(),
                MatchResponse::class.java
            )

            val dataNext = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getNextMatch(idLeague)).await(),
                MatchResponse::class.java
            )

            view.showDetailLeague(dataLeague, dataPrevious, dataNext)
            view.hideLoading()
        }
    }
}