package com.example.myapppengelolakeungan.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppengelolakeungan.AkunSayaActivity
import com.example.myapppengelolakeungan.R
import com.example.myapppengelolakeungan.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!//hanya gunakan ketikaview masih terlihat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("state fragment", "onCreateView: state")
        //inflate
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("state fragment", "onViewCreated: state")

//        //mengambil instans dari main activity
//        val viewPager2 = (activity as MainActivity).findViewById<ViewPager2>(R.id.vp_fragment)

        val layoutVertikalPengingat = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPengingatKeuangan.layoutManager = layoutVertikalPengingat
        binding.rvPengingatKeuangan.adapter = HomePengingatAdapter(arrayOf("halo","kdjf", "kdjf", "jdj", "kjdjjf"))

        val layoutVertikalTransaksi = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvTransaksiTerakhir.layoutManager = layoutVertikalTransaksi
        binding.rvTransaksiTerakhir.isNestedScrollingEnabled = false
        binding.rvTransaksiTerakhir.adapter = HomeTransaksiAdapter(arrayOf("df","kdjf","kjdf","kdjf","ldf"))

        binding.imgvshapProfile.setOnClickListener(this)
        binding.imgvBgTap.setOnClickListener(this)
        binding.imgvContact.setOnClickListener(this)
        binding.imgvTujuanKeuangan.setOnClickListener(this)
        binding.totalSaldoAsset.setOnClickListener(this)
        binding.tabunganAsset.setOnClickListener(this)
        binding.dompetKamuAsset.setOnClickListener(this)
        binding.imgvExpand.setOnClickListener(this)
        binding.tvLihatDetailTransaksi.setOnClickListener(this)

//        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
//
//        val items = arrayListOf(
//            Pengeluaran(""),
//            Kabar(""),
//            TransaksiTerakhir(""),
//            PengingatPembayaran(""),
//            UndangTeman(""),
//            AnggaranKamu(""),
//            TujuanTabungan("")
//        )
//
//        val adapter = ListBerandaAdapter(items)
//        binding.rvHome.adapter = adapter

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // menjaga fragment tidak di destroy
//        retainInstance = true
//    }

    override fun onDestroyView() {
        Log.i("state fragment", "onDestroyView: state")
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.imgvshap_profile -> {
                val intentToAkunSaya = Intent(requireContext(), AkunSayaActivity::class.java)
                startActivity(intentToAkunSaya)
            }
            R.id.imgv_bg_tap -> {

            }
            R.id.imgv_contact -> {

            }
            R.id.imgv_tujuan_keuangan -> {

            }
            R.id.total_saldo_asset -> {

            }
            R.id.tabungan_asset -> {

            }
            R.id.dompet_kamu_asset -> {

            }
            R.id.imgv_expand -> {

            }
            R.id.tv_lihat_detail_transaksi -> {

            }

        }
    }
}