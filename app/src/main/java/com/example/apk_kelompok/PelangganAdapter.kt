package com.example.apk_kelompok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_kelompok.Database.Pelanggan
import kotlinx.android.synthetic.main.adapter_pelanggan.view.*


class PelangganAdapter (private val AllPelanggan: ArrayList<Pelanggan>, private val listener: OnAdapterListener) : RecyclerView.Adapter<PelangganAdapter.PelangganViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelangganViewHolder {
        return PelangganViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_pelanggan, parent, false)
        )
    }

    override fun getItemCount() = AllPelanggan.size

    override fun onBindViewHolder(holder: PelangganViewHolder, position: Int) {
        val pelanggann = AllPelanggan[position]
        holder.view.text_nama.text = pelanggann.nama
        holder.view.text_nama.setOnClickListener {
            listener.onClick(pelanggann)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(pelanggann)
        }

    }

    class PelangganViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Pelanggan>) {
        AllPelanggan.clear()
        AllPelanggan.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(pelanggan: Pelanggan)
        fun onDelete(pelanggan: Pelanggan)
    }

}