package com.example.bwawisata.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bwaCheckout.checkout.CheckoutAdapter
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Checkout
import com.example.bwawisata.home.model.Wisata
import com.example.bwawisata.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_pilih_tiket.*

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout> /* = java.util.ArrayList<com.example.bwawisata.home.model.Checkout> */

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total yang harus dibayar", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(dataList){

        }

        btn_tiket.setOnClickListener {
            var intent = Intent(this, CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}