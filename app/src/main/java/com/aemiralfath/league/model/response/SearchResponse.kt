package com.aemiralfath.league.model.response

import com.aemiralfath.league.model.item.MatchItem

data class SearchResponse(
    val event: ArrayList<MatchItem>
)