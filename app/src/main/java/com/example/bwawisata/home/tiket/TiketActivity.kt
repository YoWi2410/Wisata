package com.example.bwawisata.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bwaCheckout.checkout.TiketAdapter
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Checkout
import com.example.bwawisata.home.model.Wisata
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        var data = intent.getParcelableExtra<Wisata>("data")

        tv_wisata.text=data!!.judul
        tv_tempat.text=data!!.tempat
        tv_rate.text=data!!.rating

        Glide.with(this)
            .load(data.poster)
            .into((iv_poster_image))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("Dewasa", ""))
        dataList.add(Checkout("Anak", ""))

        rc_checkout.adapter = TiketAdapter(dataList){

        }
    }
}