package com.example.bwaCheckout.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bwawisata.R
import com.example.bwawisata.home.model.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener:(Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val tvTiket:TextView = view.findViewById(R.id.tv_tiket)
        private val tvHarga:TextView = view.findViewById(R.id.tv_harga)

        fun bindItem(data:Checkout, listener: (Checkout) -> Unit, context: Context) {

            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.tiket!!.startsWith("Total")) {
                tvTiket.setText(data.tiket)
                tvTiket.setCompoundDrawables(null, null, null, null)
            } else {
                tvTiket.setText("Tiket tipe"+data.tiket)
            }

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }

}
