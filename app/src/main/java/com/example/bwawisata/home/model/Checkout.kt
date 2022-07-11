package com.example.bwawisata.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkout (
    var tiket:String ? = "",
    var harga:String ? = ""
) : Parcelable {
}