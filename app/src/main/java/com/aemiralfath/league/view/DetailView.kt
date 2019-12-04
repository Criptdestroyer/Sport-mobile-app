package com.aemiralfath.league.view

import com.aemiralfath.league.model.api.DetailLeagueResponse

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeague(data: DetailLeagueResponse)
}