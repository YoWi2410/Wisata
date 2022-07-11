package com.example.bwawisata.home.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions
import com.example.bwawisata.DetailActivity
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Wisata
import com.example.bwawisata.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Wisata>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Wisata")

        tv_nama.setText(preferences.getValues("nama"))
        if(preferences.getValues("saldo").equals("")) {
            currency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }
        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        rv_now_populer.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_coming_soon.layoutManager = LinearLayoutManager(context)

        getData()
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapShot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapshot in dataSnapShot.children) {
                    var wisata = getdataSnapshot.getValue(Wisata::class.java)
                    dataList.add(wisata!!)
                }

                rv_now_populer.adapter = NowPopulerAdapter(dataList) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                rv_coming_soon.adapter = ComingSoonAdapter(dataList) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getData() {

    }

    private fun currency(harga : Double, textview : TextView){
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textview.setText(format.format(harga))
    }

}
