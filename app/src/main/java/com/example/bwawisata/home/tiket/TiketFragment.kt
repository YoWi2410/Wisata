package com.example.bwawisata.home.tiket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bwawisata.R
import com.example.bwawisata.home.dashboard.ComingSoonAdapter
import com.example.bwawisata.home.model.Wisata
import com.example.bwawisata.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tiket.*


/**
 * A simple [Fragment] subclass.
 */
class TiketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Wisata>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!)
        mDatabase = FirebaseDatabase.getInstance().getReference("Wisata")

        rc_tiket.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()
                for(getdataSnapshot in p0.children){
                    val wisata = getdataSnapshot.getValue(Wisata::class.java)
                    dataList.add(wisata!!)
                }

                rc_tiket.adapter = ComingSoonAdapter(dataList){
                    var intent = Intent(context, TiketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_total.setText("${dataList.size} Wisata")
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, ""+p0.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}
