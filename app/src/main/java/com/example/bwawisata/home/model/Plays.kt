package com.example.bwawisata.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plays (
    var url:String ? = ""
) : Parcelable {
}