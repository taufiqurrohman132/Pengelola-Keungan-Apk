//package com.example.myapppengelolakeungan
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import androidx.viewpager2.adapter.FragmentStateAdapter
//
//class FragmentMainAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
//    override fun getItemCount(): Int = 4
//
//    //tampilkan fragment berdasarkan posisi atau index
//    override fun createFragment(position: Int): Fragment {
//        //cek
//        var fragment = Fragment()
//        when(position){
//            0 -> fragment = HomeFragment()
//            1 -> fragment = TransactionFragment()
//            2 -> fragment = AnggaranFragment()
//            3 -> fragment = LaporanFragment()
//        }
//        return fragment
//    }
//}