package com.example.myapppengelolakeungan.anggaran

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapppengelolakeungan.AnggaranBerulangFragment
import com.example.myapppengelolakeungan.AnggaranKamuFragment

class FragmentAnggaranAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = AnggaranKamuFragment()
            1 -> fragment = AnggaranBerulangFragment()
        }
        return fragment
    }
}
