package com.aemiralfath.league.view.view

import com.aemiralfath.league.model.response.DetailLeagueResponse
import com.aemiralfath.league.model.response.MatchResponse

interface DetailLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeague(dataLeague: DetailLeagueResponse, dataPrevious: MatchResponse, dataNext: MatchResponse)
}