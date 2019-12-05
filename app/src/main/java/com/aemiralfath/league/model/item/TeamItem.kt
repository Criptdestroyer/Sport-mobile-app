package com.aemiralfath.league.model.item


import com.google.gson.annotations.SerializedName

data class TeamItem(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,

    @SerializedName("strStadium")
    var strTeamStadium: String? = null
)