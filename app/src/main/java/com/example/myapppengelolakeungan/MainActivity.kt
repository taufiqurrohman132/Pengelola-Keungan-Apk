package com.example.myapppengelolakeungan

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapppengelolakeungan.anggaran.AnggaranFragment
import com.example.myapppengelolakeungan.databinding.ActivityMainBinding
import com.example.myapppengelolakeungan.home.HomeFragment
import com.example.myapppengelolakeungan.laporan.LaporanFragment
import com.example.myapppengelolakeungan.transaksi.TransactionFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var activeFragment: Fragment? = null
//    private lateinit var viewPager2: ViewPager2

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SELECTED_NAV", binding.bottomNavigation.selectedItemId)
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //buat status bar custom
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //icon jadi gelap ketika bg terang
                         )

        //menonaktifkan icon nav
        binding.bottomNavigation.menu.findItem(R.id.a).setEnabled(false)

        // tambahkan fragment kefragment manager tanpa menghancurkan

        // fragment awal
        if (savedInstanceState != null){
            Log.i("cek", "onCreate: ${savedInstanceState != null}")
            val selected = savedInstanceState.getInt("SELECTED_NAV")
            binding.bottomNavigation.selectedItemId = selected
            when(binding.bottomNavigation.selectedItemId){
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_transaction -> loadFragment(TransactionFragment())
                R.id.nav_anggaran -> loadFragment(AnggaranFragment())
                R.id.nav_laporan -> loadFragment(LaporanFragment())
            }
        }else{
            Log.i("cek", "onCreate:  null}")
//            supportFragmentManager.beginTransaction().show(HomeFragment()).commit()
            loadFragment(HomeFragment())
//            loadFragment("BERANDA")
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }

        if (
            !(HomeFragment().isAdded &&
            TransactionFragment().isAdded &&
            AnggaranFragment().isAdded &&
            LaporanFragment().isAdded )
            ){
            supportFragmentManager.beginTransaction().apply {
                Log.i("add", "onCreate: isadd")
                add(R.id.fragment_container, HomeFragment(), "BERANDA").hide(HomeFragment())
                add(R.id.fragment_container, TransactionFragment(), "TRANSAKSI").hide(TransactionFragment())
                add(R.id.fragment_container, AnggaranFragment(), "ANGGARAN").hide(AnggaranFragment())
                add(R.id.fragment_container, LaporanFragment(), "LAPORAN").hide(LaporanFragment())
            }.commit()
        }

//        loadFragment(HomeFragment())

//        binding.vpFragment.isUserInputEnabled = false

        // memodifikasi behavior transisi swip
//        binding.vpFragment.setPageTransformer{ page, position ->
//            val sensitivity = 1f // nilai sensitivitas transisi
////            page.translationX = -position * page.width / sensitivity // menyembunyikan swip horizontal
//            page.alpha = 1 - Math.abs(position) //* sensitivity
//            Log.i("sensi", "translationx : ${page.translationX.toString()}" +
//                    "position : ${position.toString()}" +
//                    "page witdh : ${page.width.toString()}" +
//                    "page alpa : ${page.alpha.toString()}")
//        }

        // pemnggilan func

//        binding.vpFragment.overScrollMode = View.OVER_SCROLL_NEVER

//        binding.vpFragment.registerOnPageChangeCallback( object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                binding.fideOverlay.visibility = View.VISIBLE
//                binding.fideOverlay.alpha = 1f
//
//                // animasi fade out
//                binding.fideOverlay.animate()
//                    .alpha(0f)
//                    .setDuration(700)
//                    .withEndAction {
//                        binding.fideOverlay.visibility = View.GONE
//                    }
//            }
//        })
//        binding.vpFragment.adapter = FragmentMainAdapter(this)
        //hubungkan bottom nav dengan fragment containter
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
//                    loadFragment("Beranda")
                    true
                }
                R.id.nav_transaction -> {
                    loadFragment(TransactionFragment())
//                    loadFragment("Transaksi")
                    true
                }
                R.id.nav_anggaran -> {
//                    loadFragment("Anggaran")
                    loadFragment(AnggaranFragment())
                    true
                }
                R.id.nav_laporan -> {
//                    loadFragment("Laporan")
                    loadFragment(LaporanFragment())
                    true
                }
                else -> false
            }
//            true
        }

        //hubungkan viewpager dengan bottom nav
//        binding.vpFragment.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                when(position){
//                    0 -> {
//                        binding.bottomNavigation.selectedItemId = R.id.nav_home
//                    }
//                    1 -> {
//                        binding.bottomNavigation.selectedItemId = R.id.nav_transaction
//                    }
//                    2 -> {
//                        binding.bottomNavigation.selectedItemId = R.id.nav_anggaran
//                    }
//                    3 -> {
//                        binding.bottomNavigation.selectedItemId = R.id.nav_laporan
//                    }
//                }
//            }
//        })

        binding.navAdd.setOnClickListener{
            loadFragment(TransactionFragment())
            binding.bottomNavigation.selectedItemId = R.id.nav_transaction
        }
    }

//    private fun loadFragment(fragment: Fragment){
////        if (fragment == activeFragment) return
//        // ketika fragment yang di pilih itu sama, maka kode di bawahnya tidak di jalankan. misal ketika bottom nav yang di klik sama
//        supportFragmentManager.beginTransaction().apply {
//            if (fragment.isAdded){
//                Log.i("add", "loadFragment: isadd")
//
//            activeFragment?.let { hide(it) }// sembunyikan frgment yang aktif saat ini
//            show(fragment) // tampilkan frgment yang baru
//            } else{
//                Log.i("add", "loadFragment: not isadd")
////                replace(R.id.fragment_container, fragment)
////                add(R.id.fragment_container, fragment).show(fragment)
//                show(fragment)
//            }
//            commitAllowingStateLoss()
//        }
//
//        activeFragment = fragment
////            .replace(R.id.fragment_container, fragment)//fragment = yang diload
////            .setReorderingAllowed(true)
////            .commit()
//
//        Log.d("ActiveFragment", "Current Fragment: ${activeFragment?.javaClass?.simpleName}")
//        binding.fideOverlay.visibility = View.VISIBLE
//        binding.fideOverlay.alpha = 1f
//        binding.fideOverlay.animate()
//            .alpha(0f)
//            .setDuration(500)
//            .withEndAction {
//                binding.fideOverlay.visibility = View.GONE
//            }
//    }
//    private fun loadFragment(fragment: String){
//        val fragmentTag = fragment::class.java.simpleName
//
//        //akses tag dari backstack
//        val existingFragment = supportFragmentManager.findFragmentByTag(fragment)
//    if (existingFragment != null && existingFragment != activeFragment){
//        supportFragmentManager.beginTransaction().apply {
//            show(existingFragment)
//            activeFragment?.let { hide(it) }
//        }.commit()
//    }else{
//        Log.w("null", "loadFragment: fragment null")
//    }
//    activeFragment = existingFragment
////        if (existingFragment != null){
////            Log.i("backstack", "loadFragment: no Backstak")
////            supportFragmentManager.popBackStack(fragmentTag, 0)
////        } else{
////            Log.i("backstack", "loadFragment: Backstak")
////            supportFragmentManager.beginTransaction()
////                .replace(R.id.fragment_container,  fragment, fragmentTag)
//////                .addToBackStack(fragmentTag)
////                .setReorderingAllowed(true)
////                .commit()
////        }
//
//        binding.fideOverlay.visibility = View.VISIBLE
//        binding.fideOverlay.alpha = 1f
//        binding.fideOverlay.animate()
//            .alpha(0f)
//            .setDuration(500)
//            .withEndAction {
//                binding.fideOverlay.visibility = View.GONE
//            }
//    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
        }.commit()

        binding.fideOverlay.visibility = View.VISIBLE
        binding.fideOverlay.alpha = 1f
        binding.fideOverlay.animate()
            .alpha(0f)
            .setDuration(500)
            .withEndAction {
                binding.fideOverlay.visibility = View.GONE
            }
    }

    private fun switchAnimate(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.commit()
    }

    private fun customSwipViewPager2(sensitivity: Int){
//        val recyclerView = binding.vpFragment.getChildAt(0) as RecyclerView

        // akses dan ubah nilai touchslop
        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true

        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop
//        touchSlopField.set(recyclerView, touchSlop * sensitivity)
    }
}

//class CustomViewPager2(context: Context, attrs: AttributeSet) : ViewPager2(context, attrs){
//    init {
//
//    }
//
//    private fun reduceSensitivity(){
//
//    }
//}