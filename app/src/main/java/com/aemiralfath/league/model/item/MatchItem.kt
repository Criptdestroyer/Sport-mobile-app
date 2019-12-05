package com.aemiralfath.league.model.item

import com.google.gson.annotations.SerializedName

data class MatchItem(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strEvent")
    var eventName: String? = null,

    @SerializedName("strSport")
    var sportName: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var scoreHome: Int? = null,

    @SerializedName("intAwayScore")
    var scoreAway: Int? = null,

    @SerializedName("dateEvent")
    var eventDate: String? = null,

    @SerializedName("strTime")
    var eventTime: String? = null,

    @SerializedName("strLeague")
    var LeagueName: String? = null,

    @SerializedName("strHomeFormation")
    var homeFormation: String? = null,

    @SerializedName("srtAwayFormation")
    var awayFormation: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoal: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoal: String? = null,

    @SerializedName("strHomeYellowCards")
    var homeYellow: String? = null,

    @SerializedName("strAwayYellowCards")
    var awayYellow: String? = null,

    @SerializedName("strHomeRedCards")
    var homeRed: String? = null,

    @SerializedName("strAwayRedCards")
    var awayRed: String? = null,

    @SerializedName("intHomeShots")
    var homeShots: String? = null,

    @SerializedName("intAwayShots")
    var awayShots: String? = null
)