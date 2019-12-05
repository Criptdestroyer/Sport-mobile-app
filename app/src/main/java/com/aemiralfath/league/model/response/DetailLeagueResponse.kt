package com.aemiralfath.league.model.response

import com.aemiralfath.league.model.item.DetailLeagueItem

data class DetailLeagueResponse(
    val leagues: List<DetailLeagueItem>
)