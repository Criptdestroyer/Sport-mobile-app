package com.aemiralfath.league

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val id: String?, val name: String?, val description: String?, val image: Int?) :
    Parcelable