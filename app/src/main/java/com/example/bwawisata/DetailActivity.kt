package com.example.bwawisata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bwaPlays.home.dashboard.PlaysAdapter
import com.example.bwawisata.checkout.PilihTiketActivity
import com.example.bwawisata.home.model.Plays
import com.example.bwawisata.home.model.Wisata
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Wisata>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Wisata")
            .child(data!!.judul.toString())
            .child("play")

        tv_tempat.text = data.judul
        tv_tempat.text = data.tempat
        tv_desc.text = data.desc
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster)

        rv_foto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_beli_tiket.setOnClickListener {
             var intent = Intent(this@DetailActivity, PilihTiketActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()

                for (getdataSnapshot in p0.children){
                    var Wisata = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(Wisata!!)
                }

                rv_foto.adapter = PlaysAdapter(dataList){

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+p0.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}