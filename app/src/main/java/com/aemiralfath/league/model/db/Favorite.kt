package com.aemiralfath.league.model.db

data class Favorite(
    val id: Long?,
    val eventId: String?,
    val eventName: String?,
    val scoreHome: String?,
    val scoreAway: String?,
    val badgeHome: String?,
    val badgeAway: String?
){
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val SCORE_HOME: String = "EVENT_HOME"
        const val SCORE_AWAY: String = "EVENT_AWAY"
        const val BADGE_HOME: String = "EVENT_HOME"
        const val BADGE_AWAY: String = "EVENT_AWAY"
    }
}