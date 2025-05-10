package com.example.wisata_indonesia_1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wisata(
    val id: Int,
    val nama_wisata: String,
    val lokasi_wisata: String,
    val deskripsi_wisata: String
) : Parcelable
