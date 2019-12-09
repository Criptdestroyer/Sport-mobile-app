package com.aemiralfath.league.model.api

import com.aemiralfath.league.BuildConfig

object TheSportDBApi {
    fun getDetailLeague(idLeague: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupleague.php?id="+idLeague
    }

    fun getNextMatch(idLeague: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventsnextleague.php?id="+idLeague
    }

    fun getPreviousMatch(idLeague: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/eventspastleague.php?id="+idLeague
    }

    fun getDetailMatch(idEvent: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupevent.php?id="+idEvent
    }

    fun getDetailTeam(idTeam: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/lookupteam.php?id="+idTeam
    }

    fun searchMatch(query: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}"+"/searchevents.php?e="+query
    }
}