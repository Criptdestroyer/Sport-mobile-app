package com.aemiralfath.league.view.view

import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.response.TeamResponse

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(
        dataMatch: MatchResponse,
        dataTeamHome: TeamResponse,
        dataTeamAway: TeamResponse
    )
}