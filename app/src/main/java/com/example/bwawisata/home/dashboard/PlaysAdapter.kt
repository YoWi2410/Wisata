package com.example.bwaPlays.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Plays

class PlaysAdapter(private var data: List<Plays>,
                   private val listener:(Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaysAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_play, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context) {

            Glide.with(context)
                .load(data.url)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }

}
