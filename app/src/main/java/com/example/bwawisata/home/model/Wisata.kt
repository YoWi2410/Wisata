package com.example.bwawisata.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wisata (
    var desc:String ? = "",
    var alamat:String ? = "",
    var tempat:String ? = "",
    var judul:String ? = "",
    var poster:String ? = "",
    var rating:String ? = ""
) : Parcelable {
}