package com.example.myapppengelolakeungan.transaksi

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapppengelolakeungan.TransaksiFragBerulang
import com.example.myapppengelolakeungan.TransaksiFragRiwayat

class FragmentTransaksiAdapater(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = TransaksiFragRiwayat()
            1 -> fragment = TransaksiFragBerulang()
        }
        return fragment
    }
}