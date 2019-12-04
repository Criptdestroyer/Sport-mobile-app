package com.aemiralfath.league.model.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItem(
    val id: String?,
    val name: String?,
    val image: Int?
) :
    Parcelable