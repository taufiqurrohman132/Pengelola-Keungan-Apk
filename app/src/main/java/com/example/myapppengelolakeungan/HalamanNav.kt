package com.example.myapppengelolakeungan

import android.annotation.SuppressLint
import android.app.LauncherActivity.ListItem
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapppengelolakeungan.databinding.ActivityAkunSayaBinding
import com.example.myapppengelolakeungan.databinding.ActivityMainBinding
import com.example.myapppengelolakeungan.databinding.FragmentAnggaranBinding
import com.example.myapppengelolakeungan.databinding.FragmentHomeBinding
import com.example.myapppengelolakeungan.databinding.FragmentTransactionBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt

//class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
//
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!//hanya gunakan ketikaview masih terlihat
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        Log.i("state fragment", "onCreateView: state")
//        //inflate
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        Log.i("state fragment", "onViewCreated: state")
//
////        //mengambil instans dari main activity
////        val viewPager2 = (activity as MainActivity).findViewById<ViewPager2>(R.id.vp_fragment)
//
//        binding.imgvshapProfile.setOnClickListener(this)
//        binding.imgvBgTap.setOnClickListener(this)
//        binding.imgvContact.setOnClickListener(this)
//        binding.imgvTujuanKeuangan.setOnClickListener(this)
//        binding.totalSaldoAsset.setOnClickListener(this)
//        binding.tabunganAsset.setOnClickListener(this)
//        binding.dompetKamuAsset.setOnClickListener(this)
//        binding.imgvExpand.setOnClickListener(this)
//        binding.tvLihatDetailTransaksi.setOnClickListener(this)
//
////        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
////
////        val items = arrayListOf(
////            Pengeluaran(""),
////            Kabar(""),
////            TransaksiTerakhir(""),
////            PengingatPembayaran(""),
////            UndangTeman(""),
////            AnggaranKamu(""),
////            TujuanTabungan("")
////        )
////
////        val adapter = ListBerandaAdapter(items)
////        binding.rvHome.adapter = adapter
//
//    }
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////        // menjaga fragment tidak di destroy
////        retainInstance = true
////    }
//
//    override fun onDestroyView() {
//        Log.i("state fragment", "onDestroyView: state")
//        super.onDestroyView()
//        _binding = null
//    }
//
//    override fun onClick(p0: View?) {
//        when(p0?.id){
//            R.id.imgvshap_profile -> {
//                val intentToAkunSaya = Intent(requireContext(), AkunSayaActivity::class.java)
//                startActivity(intentToAkunSaya)
//            }
//            R.id.imgv_bg_tap -> {
//
//            }
//            R.id.imgv_contact -> {
//
//            }
//            R.id.imgv_tujuan_keuangan -> {
//
//            }
//            R.id.total_saldo_asset -> {
//
//            }
//            R.id.tabungan_asset -> {
//
//            }
//            R.id.dompet_kamu_asset -> {
//
//            }
//            R.id.imgv_expand -> {
//
//            }
//            R.id.tv_lihat_detail_transaksi -> {
//
//            }
//
//        }
//    }
//}

//class TransactionFragment : Fragment(R.layout.fragment_transaction){
//    private var _binding: FragmentTransactionBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var fragmentTransaksiAdapter: FragmentTransaksiAdapater
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        fragmentTransaksiAdapter = FragmentTransaksiAdapater(this)
//        binding.vpTransaksi.adapter = fragmentTransaksiAdapter
//
//        TabLayoutMediator(binding.tablayTransaksi, binding.vpTransaksi) { tab, position ->
//            when(position){
//                0 -> tab.text = "Riwayat Transaksi"
//                1 -> tab.text = "Transaksi Berulang"
//            }
//        }.attach()
//
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
//}

//class AnggaranFragment : Fragment(R.layout.fragment_anggaran){
//    private var _binding: FragmentAnggaranBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var fragmentAnggaranAdapter: FragmentAnggaranAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentAnggaranBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //inisialisasi adapter
//        fragmentAnggaranAdapter = FragmentAnggaranAdapter(this)
//        binding.vpAnggaran.adapter = fragmentAnggaranAdapter
//        TabLayoutMediator(binding.tabLayAnggaran, binding.vpAnggaran){ tab, position ->
//            when(position){
//                0 -> tab.text = "Anggaran Kamu"
//                1 -> tab.text = "Anggaran Berulang"
//            }
//        }.attach()
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
//
//}

//class LaporanFragment : Fragment(R.layout.fragment_laporan){
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////        // menjaga fragment tidak di destroy
////        retainInstance = true
////    }
//
//}

//class FragmentTransaksiAdapater(fragment: Fragment) : FragmentStateAdapter(fragment) {
//    override fun getItemCount(): Int = 2
//
//    override fun createFragment(position: Int): Fragment {
//        var fragment = Fragment()
//        when(position){
//            0 -> fragment = TransaksiFragRiwayat()
//            1 -> fragment = TransaksiFragBerulang()
//        }
//        return fragment
//    }
//}

//class FragmentAnggaranAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
//    override fun getItemCount(): Int = 2
//
//    override fun createFragment(position: Int): Fragment {
//        var fragment = Fragment()
//        when(position){
//            0 -> fragment = AnggaranKamuFragment()
//            1 -> fragment = AnggaranBerulangFragment()
//        }
//        return fragment
//    }
//}
