package com.example.bwawisata.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Checkout
import com.example.bwawisata.home.model.Wisata
import kotlinx.android.synthetic.main.activity_pilih_tiket.*

class PilihTiketActivity : AppCompatActivity() {

    var statusbtn_tambah_tiket1:Boolean = false
    var statusbtn_tambah_tiket2:Boolean = false
    var total:Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_tiket)

        val data = intent.getParcelableExtra<Wisata>("data")
        tv_tempat.text = data!!.judul

        btn_tambah_tiket1.setOnClickListener {
            if (statusbtn_tambah_tiket1){
                total -= 1
                beliTiket(total)
            }else{
                total +=1
                beliTiket(total)

                val data = Checkout("Tiket Dewasa", "20000")
                dataList.add(data)
            }
        }
        btn_tambah_tiket2.setOnClickListener {
            if (statusbtn_tambah_tiket2){
                total -= 1
                beliTiket(total)
            }else{
                total +=1
                beliTiket(total)

                val data = Checkout("Tiket Anaka", "15000")
                dataList.add(data)
            }
        }

        btn_home.setOnClickListener {
            var intent = Intent(this, CheckoutActivity::class.java)
                .putExtra("data", dataList)
            startActivity(intent)
        }
    }

    private fun beliTiket(total: Int) {
        if(total == 0){
            btn_home.setText("Beli Tiket")
            btn_home.visibility = View.INVISIBLE
        }else{
            btn_home.setText("Beli Tiket ("+total+")")
            btn_home.visibility = View.VISIBLE
        }
    }
}