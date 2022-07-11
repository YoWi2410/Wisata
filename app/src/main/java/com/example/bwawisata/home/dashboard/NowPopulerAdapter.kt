package com.example.bwawisata.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Wisata

class NowPopulerAdapter(private var data: List<Wisata>,
                        private val listener:(Wisata) -> Unit)
    : RecyclerView.Adapter<NowPopulerAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPopulerAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_now_populer, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NowPopulerAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val tvTiket:TextView = view.findViewById(R.id.tv_tiket)
        private val tvTempat:TextView = view.findViewById(R.id.tv_tempat)
        private val tvRate:TextView = view.findViewById(R.id.tv_rate)

        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Wisata, listener: (Wisata) -> Unit, context: Context) {
            tvTiket.setText(data.judul)
            tvTempat.setText(data.tempat)
            tvRate.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }

}
