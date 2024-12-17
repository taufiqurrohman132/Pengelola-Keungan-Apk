package com.example.myapppengelolakeungan

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.example.myapppengelolakeungan.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authFB: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authFB = Firebase.auth

        //buat status bar custom
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //icon jadi gelap ketika bg terang
                )

        Glide.with(this)
            .load(R.drawable.show_password)
            .circleCrop()
            .into(binding.showPassword)

//        Glide.with(this)
//            .load(R.drawable.not_show_password)
//            .circleCrop()
//            .into(binding.showPassword)


        //move ke daftar
        binding.tvDaftarDisini.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val moveToDaftar = Intent(this, DaftarAkunActivity::class.java)
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                moveToDaftar.putExtra(DaftarAkunActivity.EXTRA_EMAIL_ADDRESS, email)
            }
            startActivity(moveToDaftar)
        }

        warningEmailRealTime()

        binding.btnLogin.setOnClickListener(this)
    }

    public override fun onStart() {
        super.onStart()
        //cek apakah sudad login
        val currentUser = authFB.currentUser
        when{
             currentUser != null -> {
                 currentUser.reload()
                 val intentToMain = Intent(this, MainActivity::class.java)
                 startActivity(intentToMain)
             }
        }
    }

    private fun signUser(email: String, password: String){
        authFB.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                val intentToMain = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentToMain)
                Toast.makeText(baseContext, "Login berhasil", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(baseContext, "Login gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun warningEmailRealTime(){
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        binding.edtEmail.addTextChangedListener(object : TextWatcher {//texwatcher = untuk memantau perubahan secara real time
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(p0: Editable?) {
                //jika teks tidak cocok degan pola email, tampilkan warning
                if (!p0.toString().matches(emailPattern)){
                    binding.tvWarningEmail.text = "Format email tidak valid"
                    binding.tvWarningEmail.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 16
                    }
                    binding.edtEmail.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.red)

                    if (p0.toString().trim().isBlank()){
                        binding.tvWarningEmail.text = "Silahkan masukkan email kamu"
                        binding.tvWarningEmail.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                            bottomMargin = 16
                        }
                        binding.edtEmail.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.red)
                    }
                } else{
                    binding.tvWarningEmail.text = ""
                    binding.tvWarningEmail.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 0
                    }
                    binding.edtEmail.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.blue)
                }
            }
        })
    }

    private fun warningPasswordRealtime(){
        binding.edtPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().trim().isBlank()){
                    binding.tvWarningPassword.text = "Password harus di isi"
                    binding.tvWarningPassword.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 16
                    }
                    binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.red)
                }else{
                    binding.tvWarningPassword.text = ""
                    binding.tvWarningPassword.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 0
                    }
                    binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.blue)
                }
            }
        })
    }

    private fun warningEmail(email: String){
        val benar = (email == "")
        Log.i("warnimg", "warningEmailAndPassword: $benar")
        if (email.isBlank()){
            binding.tvWarningEmail.text = "Silahkan masukkan email kamu"
            binding.tvWarningEmail.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = 16
            }
            binding.edtEmail.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.red)
        }
    }

    override fun onClick(v: View?) {
        Log.i("click", "onClick: $v")
        when (v?.id){
            R.id.btn_login -> {
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()

                warningEmail(email.trim())
                warningPasswordRealtime()
                if (password.trim().isEmpty()){
                    binding.tvWarningPassword.text = "Password harus di isi"
                    binding.tvWarningPassword.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        bottomMargin = 16
                    }
                    binding.edtPassword.backgroundTintList = ContextCompat.getColorStateList(this@LoginActivity, R.color.red)
                }
                Log.i("login", "onClick: ${binding.edtEmail.toString()} ${binding.edtPassword.toString()}")

                signUser(email, password)

                binding.btnLogin.isEnabled = false
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.btnLogin.isEnabled = true
                }, 2000)
            }
        }
    }

    //akses informasi user
    private fun getCurrentUser(){
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
        }
    }
}