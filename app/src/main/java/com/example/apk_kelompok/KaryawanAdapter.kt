package com.example.apk_kelompok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_kelompok.Database.Karyawan
import kotlinx.android.synthetic.main.adapter_karyawan.view.*


class KaryawanAdapter (private val AllKaryawan: ArrayList<Karyawan>, private val listener: OnAdapterListener) : RecyclerView.Adapter<KaryawanAdapter.KaryawanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaryawanViewHolder {
        return KaryawanViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_karyawan, parent, false)
        )
    }

    override fun getItemCount() = AllKaryawan.size

    override fun onBindViewHolder(holder: KaryawanViewHolder, position: Int) {
        val karyawan = AllKaryawan[position]
        holder.view.text_nama.text = karyawan.nama
        holder.view.text_nama.setOnClickListener {
            listener.onClick(karyawan)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(karyawan)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(karyawan)
        }

    }

    class KaryawanViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Karyawan>) {
        AllKaryawan.clear()
        AllKaryawan.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(karyawan: Karyawan)
        fun onDelete(karyawan: Karyawan)
        fun onUpdate(karyawan: Karyawan)
    }

}