package com.example.wisata_indonesia_1.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wisata_indonesia_1.R

class FormWisataActivity : AppCompatActivity() {
    private lateinit var inputNama: EditText
    private lateinit var inputLokasi: EditText
    private lateinit var inputKeterangan: EditText
    private lateinit var tombolSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wisata)

        inisialisasiKomponen()
        setupAksiTombol()
    }

    private fun inisialisasiKomponen() {
        inputNama = findViewById(R.id.editTextName)
        inputLokasi = findViewById(R.id.editTextLocation)
        inputKeterangan = findViewById(R.id.editTextDeskripsi)
        tombolSubmit = findViewById(R.id.buttonSimpan)
    }

    private fun setupAksiTombol() {
        tombolSubmit.setOnClickListener {
            prosesSimpanData()
        }
    }

    private fun prosesSimpanData() {
        val namaWisata = inputNama.text.toString().trim()
        val lokasiWisata = inputLokasi.text.toString().trim()
        val deskripsiWisata = inputKeterangan.text.toString().trim()

        if (namaWisata.isEmpty() || lokasiWisata.isEmpty() || deskripsiWisata.isEmpty()) {
            Toast.makeText(this, "Semua kolom wajib diisi.", Toast.LENGTH_SHORT).show()
            return
        }

        val dataKembali = Intent().apply {
            putExtra("nama_wisata", namaWisata)
            putExtra("lokasi_wisata", lokasiWisata)
            putExtra("deskripsi_wisata", deskripsiWisata)
        }

        setResult(RESULT_OK, dataKembali)
        finish()
    }
}
