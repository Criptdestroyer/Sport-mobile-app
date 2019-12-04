package com.aemiralfath.league.model.item

import com.google.gson.annotations.SerializedName

data class DetailLeagueItem(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var LeagueName: String? = null,

    @SerializedName("strBadge")
    var leagueBadge: String? = null,

    @SerializedName("strDescriptionEN")
    var leagueDescription: String? = null
)