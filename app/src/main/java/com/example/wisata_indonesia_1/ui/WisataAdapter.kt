package com.example.wisata_indonesia_1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wisata_indonesia_1.R
import com.example.wisata_indonesia_1.model.Wisata

class WisataAdapter(
    private val onItemSelected: (Wisata) -> Unit
) : RecyclerView.Adapter<WisataAdapter.WisataViewHolder>() {

    private var daftarWisata: List<Wisata> = emptyList()

    fun perbaruiData(dataBaru: List<Wisata>) {
        daftarWisata = dataBaru
        notifyDataSetChanged()
    }

    inner class WisataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val namaText: TextView = view.findViewById(R.id.tvName)
        val deskripsiText: TextView = view.findViewById(R.id.tvDescription)
        val lokasiText: TextView = view.findViewById(R.id.tvLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wisata, parent, false)
        return WisataViewHolder(layout)
    }

    override fun getItemCount(): Int = daftarWisata.size

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        val wisata = daftarWisata[position]
        holder.namaText.text = wisata.nama_wisata
        holder.deskripsiText.text = wisata.deskripsi_wisata
        holder.lokasiText.text = wisata.lokasi_wisata

        holder.itemView.setOnClickListener {
            onItemSelected(wisata)
        }
    }
}
