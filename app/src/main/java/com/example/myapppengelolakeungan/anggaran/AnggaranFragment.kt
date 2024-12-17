package com.example.myapppengelolakeungan.anggaran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapppengelolakeungan.R
import com.example.myapppengelolakeungan.databinding.FragmentAnggaranBinding
import com.google.android.material.tabs.TabLayoutMediator

class AnggaranFragment : Fragment(R.layout.fragment_anggaran){
    private var _binding: FragmentAnggaranBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragmentAnggaranAdapter: FragmentAnggaranAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnggaranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //inisialisasi adapter
        fragmentAnggaranAdapter = FragmentAnggaranAdapter(this)
        binding.vpAnggaran.adapter = fragmentAnggaranAdapter
        TabLayoutMediator(binding.tabLayAnggaran, binding.vpAnggaran){ tab, position ->
            when(position){
                0 -> tab.text = "Anggaran Kamu"
                1 -> tab.text = "Anggaran Berulang"
            }
        }.attach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}