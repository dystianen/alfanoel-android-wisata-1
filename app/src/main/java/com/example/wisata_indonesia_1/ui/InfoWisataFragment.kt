package com.example.wisata_indonesia_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.wisata_indonesia_1.R
import com.example.wisata_indonesia_1.databinding.FragmentDetailWisataBinding

class InfoWisataFragment : Fragment() {

    private var _viewBinding: FragmentDetailWisataBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentDetailWisataBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tampilkanDetailWisata()

        view.findViewById<Button>(R.id.btnBack).setOnClickListener {
            // Kembali ke layar sebelumnya
            requireActivity().supportFragmentManager.popBackStack()

            // Menampilkan kembali RecyclerView di MainActivity
            (requireActivity() as MainActivity).tampilkanRecyclerView()
        }
    }

    private fun tampilkanDetailWisata() {
        val parameter = InfoWisataFragmentArgs.fromBundle(requireArguments())
        val dataWisata = parameter.wisata

        viewBinding.tvName.text = dataWisata.nama_wisata
        viewBinding.tvDescription.text = dataWisata.deskripsi_wisata
        viewBinding.tvLocation.text = dataWisata.lokasi_wisata
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
