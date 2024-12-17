package com.example.myapppengelolakeungan

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.example.myapppengelolakeungan.databinding.ActivityDaftarAkunBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class DaftarAkunActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDaftarAkunBinding
    private lateinit var authFB: FirebaseAuth

    companion object{
        const val EXTRA_EMAIL_ADDRESS = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //buat status bar custom
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //icon jadi gelap ketika bg terang
                )

        //pemanggilan func
        warningEmailRealTime()
        warningPassword()
        warningKonfirmPasswordRealtime()

        authFB = Firebase.auth

        binding.tvWarningEmailDaftar.visibility = View.GONE
        binding.tvWarningKonfirmPassword.visibility = View.GONE
        binding.tvWarningPassword.visibility = View.GONE

        val email = intent.getStringExtra(EXTRA_EMAIL_ADDRESS)
        if (email != null) binding.edtEmailDaftar.setText(email)

        //move ke login
        binding.tvLoginDisini.setOnClickListener {
            finish()
        }

        binding.btnDaftar.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_daftar -> {
                //lakukan aksi yang di inginkan
                val email = binding.edtEmailDaftar.text.toString()
                val password = binding.edtPassword.text.toString()
                val konfirmPassword = binding.edtKonfirmPassword.text.toString()

                if (email.trim().isBlank()){
                    binding.tvWarningEmailDaftar.text = "Silahkan masukkan email kamu"
                    binding.tvWarningEmailDaftar.visibility = View.VISIBLE
                    binding.edtEmailDaftar.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                }
                if (password.trim().isBlank()){
                    binding.tvWarningPassword.text = "Password harus di isi"
                    binding.tvWarningPassword.visibility = View.VISIBLE
                    binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                }
                if (konfirmPassword.trim().isBlank()){
                    binding.tvWarningKonfirmPassword.text = "Konfirmasi Password harus di isi"
                    binding.tvWarningKonfirmPassword.visibility = View.VISIBLE
                    binding.edtKonfirmPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                }
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.trim().isNotBlank() && password.length >= 6 && password == konfirmPassword){
                    createAccount(email, password)
                    Toast.makeText(
                        baseContext,
                        "Autentikasi Sukses",
                            Toast.LENGTH_SHORT,
                        ).show()
                }else{
                    Toast.makeText(
                        baseContext,
                        "Autentikasi Gagal",
                        Toast.LENGTH_SHORT,
                        ).show()
                }
                binding.btnDaftar.isEnabled = false
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.btnDaftar.isEnabled = true
                }, 2000)
            }
        }
    }

    //menyimpan alamat email user
    private fun saveEmailToPreferences(email: String, password: String){
        val shredPref = getSharedPreferences("pref", MODE_PRIVATE)
        shredPref.edit().putString("email", email).apply()
        shredPref.edit().putString("password", password).apply()
    }
    private fun getEmailFromPreferences(): String?{
        val shredPref = getSharedPreferences("pref", MODE_PRIVATE)
        return shredPref.getString("email", null)
    }
    private fun getPasswordFromPreferences(): String?{
        val shredPref = getSharedPreferences("pref", MODE_PRIVATE)
        return shredPref.getString("password", null)
    }

    private fun warningEmailRealTime(){
        binding.edtEmailDaftar.addTextChangedListener(object : TextWatcher {//texwatcher = untuk memantau perubahan secara real time
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(email: Editable?) {
                //jika teks tidak cocok degan pola email, tampilkan warning
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.tvWarningEmailDaftar.text = "Format email tidak valid"
                    binding.tvWarningEmailDaftar.visibility = View.VISIBLE
                    binding.edtEmailDaftar.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                    if (email.toString().trim().isBlank()){
                        binding.tvWarningEmailDaftar.text = "Silahkan masukkan email kamu"
                        binding.tvWarningEmailDaftar.visibility = View.VISIBLE
                        binding.edtEmailDaftar.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                    }
                } else{
                    binding.tvWarningEmailDaftar.text = ""
                    binding.tvWarningEmailDaftar.visibility = View.GONE
                    binding.edtEmailDaftar.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.blue)
                }
            }
        })
    }

    private fun warningPassword(){
        binding.edtPassword.addTextChangedListener(object : TextWatcher {//texwatcher = untuk memantau perubahan secara real time
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(password: Editable?) {
                //jika teks tidak cocok degan pola email, tampilkan warning
                if (password.toString().trim().isBlank()){
                    binding.tvWarningPassword.text = "Password harus di isi"
                    binding.tvWarningPassword.visibility = View.VISIBLE
                    binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                }else{
                    if (password.toString().length < 6){
                        binding.tvWarningPassword.text = "Password minimal 6 karakter"
                        binding.tvWarningPassword.visibility = View.VISIBLE
                        binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                    }else{
                        binding.tvWarningPassword.text = ""
                        binding.tvWarningPassword.visibility = View.GONE
                        binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.blue)
                    }
                }
            }
        })
    }

    private fun warningKonfirmPasswordRealtime(){
        binding.edtKonfirmPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() != binding.edtPassword.text.toString()){
                    binding.tvWarningKonfirmPassword.text = "Konfirmasi Password tidak sesuai"
                    binding.tvWarningKonfirmPassword.visibility = View.VISIBLE
                    binding.edtKonfirmPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.red)
                    if (p0.toString().trim().isBlank()){
                        binding.tvWarningKonfirmPassword.text = "Konfirmasi Password harus di isi"
                    }
                }else{
                    binding.tvWarningKonfirmPassword.text = ""
                    binding.tvWarningKonfirmPassword.visibility = View.GONE
                    binding.edtKonfirmPassword.backgroundTintList = ContextCompat.getColorStateList(this@DaftarAkunActivity, R.color.blue)
                }
            }
        })
    }

    private fun createAccount(email: String, password: String){
        authFB.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                //daftar sukses
//                val user = authFB.currentUser
                saveEmailToPreferences(binding.edtEmailDaftar.text.toString(), binding.edtPassword.text.toString())
                val intentToMain = Intent(this, MainActivity::class.java)
                startActivity(intentToMain)
            }
        }
    }


//    private fun updateUI(user: FirebaseUser?){
//        if (user != null){
//
//        }
//
//    }
}