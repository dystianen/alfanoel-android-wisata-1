package com.example.wisata_indonesia_1.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisata_indonesia_1.R
import com.example.wisata_indonesia_1.model.Wisata
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var listWisataView: RecyclerView
    private lateinit var wisataAdapter: WisataAdapter
    private var dataWisata: List<Wisata> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        siapkanDataAwal()
        setupRecyclerView()
        setupFloatingActionButton()
    }

    private fun siapkanDataAwal() {
        dataWisata = listOf(
            Wisata(1, "Pantai Dewata", "Cantik", "Bali"),
            Wisata(2, "Taman Monas", "Cantik", "Jakarta"),
            Wisata(3, "Pangalengan", "Cantik", "Bandung")
        )
    }

    private fun setupRecyclerView() {
        listWisataView = findViewById(R.id.recyclerViewTasks)
        listWisataView.layoutManager = LinearLayoutManager(this)

        wisataAdapter = WisataAdapter(
            onItemSelected = { item ->
                val wisata = item
                val detailFragment = InfoWisataFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("wisata", wisata)
                    }
                }

                // Menyembunyikan RecyclerView ketika fragment dipilih
                listWisataView.visibility = View.GONE

                // Menampilkan Fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, detailFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
        )

        val jarakDp = 2
        val jarakPx = (jarakDp * resources.displayMetrics.density).toInt()
        listWisataView.addItemDecoration(VerticalSpaceItemDecoration(jarakPx))

        listWisataView.adapter = wisataAdapter
        wisataAdapter.perbaruiData(dataWisata)
    }

    private fun setupFloatingActionButton() {
        val tombolTambah: FloatingActionButton = findViewById(R.id.fabAddWisata)
        tombolTambah.setOnClickListener {
            val goToForm = Intent(this, FormWisataActivity::class.java)
            launcherTambahData.launch(goToForm)
        }
    }

    private val launcherTambahData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { hasil ->
        if (hasil.resultCode == RESULT_OK) {
            val resultData = hasil.data ?: return@registerForActivityResult
            val namaBaru = resultData.getStringExtra("nama_wisata") ?: return@registerForActivityResult
            val deskripsiBaru = resultData.getStringExtra("deskripsi_wisata") ?: ""
            val lokasiBaru = resultData.getStringExtra("lokasi_wisata") ?: return@registerForActivityResult

            val entriBaru = Wisata(
                id = dataWisata.size + 1,
                nama_wisata = namaBaru,
                deskripsi_wisata = deskripsiBaru,
                lokasi_wisata = lokasiBaru
            )

            dataWisata = dataWisata + entriBaru
            wisataAdapter.perbaruiData(dataWisata)
        }
    }

    // Fungsi baru untuk menampilkan RecyclerView (dipanggil dari InfoWisataFragment)
    fun tampilkanRecyclerView() {
        listWisataView.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Menampilkan kembali RecyclerView
        listWisataView.visibility = View.VISIBLE
    }
}