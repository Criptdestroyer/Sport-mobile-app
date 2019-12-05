package com.aemiralfath.league.model.response

import com.aemiralfath.league.model.item.MatchItem

data class MatchResponse(
    val events: ArrayList<MatchItem>
)