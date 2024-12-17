package com.example.myapppengelolakeungan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppengelolakeungan.databinding.AnggaranFragmentKamuBinding

class AnggaranKamuFragment : Fragment(R.layout.anggaran_fragment_kamu){
    private var _binding: AnggaranFragmentKamuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AnggaranFragmentKamuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutVertikal = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val layoutHorizontal = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvKamuKategori.layoutManager = layoutHorizontal
        binding.rvKamuItem.layoutManager = layoutVertikal

        val teks = arrayListOf("Bahan Makanan", "Kas", "Dompet", "Minuman")
        val imge = arrayListOf(R.drawable.seratus_saja, R.drawable.seratus_saja, R.drawable.seratus_saja, R.drawable.seratus_saja)
        binding.rvKamuKategori.adapter = AnggaranKamuKategoriAdapter(teks, imge)

        val data = arrayListOf("kdjj","kdjf", "kdjfk", "kjfkjd", "kdjfk")
        binding.rvKamuItem.adapter = AnggaranKamuItemAdapter(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
class AnggaranBerulangFragment : Fragment(R.layout.anggaran_fagment_berulang){

}
