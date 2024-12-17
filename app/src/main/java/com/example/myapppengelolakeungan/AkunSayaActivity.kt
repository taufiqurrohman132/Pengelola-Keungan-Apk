package com.example.myapppengelolakeungan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapppengelolakeungan.databinding.ActivityAkunSayaBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AkunSayaActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAkunSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAkunSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //buat status bar custom
        window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //icon jadi gelap ketika bg terang
                )

        binding.btnKeluar.setOnClickListener(this)
        binding.btnBackAkun.setOnClickListener(this)
        binding.shapimgvEditProfile.setOnClickListener(this)
        binding.tanggalPriode.setOnClickListener(this)
        binding.undangTeman.setOnClickListener(this)
        binding.menuAkunSaya.setOnClickListener(this)
        binding.menuAturPin.setOnClickListener(this)
        binding.menuBiometrik.setOnClickListener(this)
        binding.menuMode.setOnClickListener(this)
        binding.menuKelola.setOnClickListener(this)
        binding.menuReset.setOnClickListener(this)
        binding.menuBahasa.setOnClickListener(this)
        binding.menuNotif.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_keluar -> {
                Log.i("TAG", "onClick: log out")
                val auth = Firebase.auth
                auth.signOut()

                val backToLogin = Intent(this, LoginActivity::class.java)
                backToLogin.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(backToLogin)
                finish()

            }
            R.id.btn_back_akun ->{
                finish()
            }
            R.id.shapimgv_edit_profile ->{

            }
            R.id.tanggal_priode ->{

            }
            R.id.undang_teman ->{

            }
            R.id.menu_akun_saya ->{

            }
            R.id.menu_atur_pin ->{

            }
            R.id.menu_biometrik ->{

            }
            R.id.menu_mode ->{

            }
            R.id.menu_kelola ->{

            }
            R.id.menu_reset ->{

            }
            R.id.menu_bahasa ->{

            }
            R.id.menu_notif ->{

            }

        }
    }
}