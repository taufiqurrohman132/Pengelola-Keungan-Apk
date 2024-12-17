package com.example.myapppengelolakeungan.transaksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapppengelolakeungan.R
import com.example.myapppengelolakeungan.databinding.FragmentTransactionBinding
import com.google.android.material.tabs.TabLayoutMediator

class TransactionFragment : Fragment(R.layout.fragment_transaction){
    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragmentTransaksiAdapter: FragmentTransaksiAdapater

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentTransaksiAdapter = FragmentTransaksiAdapater(this)
        binding.vpTransaksi.adapter = fragmentTransaksiAdapter

        TabLayoutMediator(binding.tablayTransaksi, binding.vpTransaksi) { tab, position ->
            when(position){
                0 -> tab.text = "Riwayat Transaksi"
                1 -> tab.text = "Transaksi Berulang"
            }
        }.attach()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
