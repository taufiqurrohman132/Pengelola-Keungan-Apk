//package com.example.myapppengelolakeungan
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.graphics.Typeface
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.text.SpannableString
//import android.text.style.StyleSpan
//import android.util.Log
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapppengelolakeungan.databinding.ActivityDaftarAkunBinding
//import com.example.myapppengelolakeungan.databinding.ActivityEmailVerivyBinding
//import com.google.firebase.Firebase
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.actionCodeSettings
//import com.google.firebase.auth.auth
//import com.google.firebase.firestore.FieldValue
//import com.google.firebase.firestore.FirebaseFirestore
//
//class EmailVerivyActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityEmailVerivyBinding
//    private lateinit var bindingDaftar: ActivityDaftarAkunBinding
////    private val email = intent.getStringExtra(EXTRA_NAME)
//
//    companion object{
//        const val EXTRA_NAME = "extra name"
//        val TAG = "tag"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityEmailVerivyBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        email = intent.getStringExtra(EXTRA_NAME)
//        val email = intent.getStringExtra(EXTRA_NAME)
//        val fullText = "Cek inbox & spam emailmu, kami baru saja mengirimkan 6 digit kode verifikasi ke $email"
//        val spannable = SpannableString(fullText)
//        val data = intent.data
//
//        if (data != null){
//            val emailUser = bindingDaftar.edtEmailDaftar.text.toString()
//            if (Firebase.auth.isSignInWithEmailLink(data.toString())){
//                Firebase.auth.signInWithEmailLink(emailUser, data.toString())
//                    .addOnCompleteListener {
//                        if (it.isSuccessful){
//                            Log.i("TAG", "onCreate: succes")
//                        }
//                    }
//            }
//        }
//
//        if (email != null) {
//            spannable.setSpan(
//                StyleSpan(Typeface.BOLD),
//                0,
//                email.length,
//                SpannableString.SPAN_INCLUSIVE_INCLUSIVE
//            )
//        }
//
//        //timer mundur
//        val startTime: Long = 113000
//        //buat count down timer
//        object : CountDownTimer(startTime, 1000) {
//
//            @SuppressLint("DefaultLocale")
//            override fun onTick(milliSampaiSelesai: Long) {
//                //menghitung menit dan detik
//                val minutes = (milliSampaiSelesai / 1000) / 60
//                val second = (milliSampaiSelesai / 1000) % 60
//
//                //format waktu minute : second
//                val time = String.format("%02d:%02d", minutes, second)
//
//                //membuat teks dengan kustom tebal
//                val fullText = "$time lagi untuk mengirim ulang kode"
//                val spannable = SpannableString(fullText)
//
//                //menambahkan style tebal kustom
//                spannable.setSpan(
//                    StyleSpan(Typeface.BOLD), //mebuat teks tebal
//                    fullText.indexOf(time), //mulai dari index waktu
//                    fullText.indexOf(time) + time.length, //sampai akhir waktu
//                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
//                )
//
//                //set texview dengan teks yag telah di format
//                binding.tvWaktuVerify.text = spannable
//            }
//
//            override fun onFinish() {
//                binding.tvWaktuVerify.text ="00:00 lagi untuk mengirim ulang kode"
//            }
//        }.start()
//
//        //pemanggilan func
//        sendSignInLinkToEmail(email)
//        checkSignWithEmailLink(email)
//
//    }
//
//    private fun sendSignInLinkToEmail (email: String?){
//        val actionCodeSetting = actionCodeSettings {
//            url = "https://sratus-23deb.firebaseapp.com" //tautan bawaan fireabase
//            //ini harus true
//            handleCodeInApp = true
////            setIOSBundleId("com.example.ios")
//            setAndroidPackageName(
//                "com.example.myapppengelolakeungan",
//                true, //instal jika tidak availabel
//                "12", //minimm version
//            )
//        }
//        //kirim link autentikasi ke alamat email
//        if (email != null){
//            Log.i("TAG", "sendSignInLinkToEmail: $email")
//            Firebase.auth.sendSignInLinkToEmail(email, actionCodeSetting)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful){
//                        Log.i("TAG", "sendSignInLinkToEmail: success ")
//                        saveEmailToPreferences(bindingDaftar.edtEmailDaftar.text.toString())
//                        Toast.makeText(baseContext, "Verifikasi Berhasil", Toast.LENGTH_SHORT).show()
//                    }
//                }
//        }
//    }
//
//    private fun generatedPin(): String{
//        val randomPin = (100000..999999).random()
//        return randomPin.toString()
//    }
//
//    //simpan pin ke fire store
//    private fun savePinToFirestore(emailUser: String, pin: String){
//        val db = FirebaseFirestore.getInstance()
//
//        val pinData = hashMapOf(
//            "email" to emailUser,
//            "pin" to pin,
//            "timestamp" to FieldValue.serverTimestamp()// untuk keperluan expering pin, jika di perlukan
//        )
//
//        db.collection("pin_verification")
//            .document()
//            .set(pinData)
//            .addOnSuccessListener {
//                Log.i("firestore", "savePinToFirestore: succcess")
//            }
//            .addOnFailureListener {error ->
//                Log.w("firestore", "savePinToFirestore: gagal", )
//            }
//    }
//
//    private fun checkSignWithEmailLink(email: String?){
//        val auth = Firebase.auth
//        val intent = intent
//        val emailLink = intent.data.toString()
//
//        //Konfirmasikan bahwa tautan tersebut adalah tautan masuk dengan email.
//        if (auth.isSignInWithEmailLink(emailLink) && email != null){
//            //SDK klien akan menguraikan kode dari tautan untuk Anda.
//            auth.signInWithEmailLink(email, emailLink)
//                .addOnCompleteListener {task ->
//                    if (task.isSuccessful){
//                        Log.d(TAG, "Successfully signed in with email link!")
//                        val result = task.result
//                        //lakukan tindakan setelah berhasil masuk
//                        val intentToHome = Intent(this, MainActivity::class.java)
//                        startActivity(intentToHome)
//
//                    }else{
//                        Log.e(TAG, "Error signing in with email link", task.exception)
//                    }
//                }
//        }
//    }
//    //menyimpan alamat email user
//    private fun saveEmailToPreferences(email: String){
//        val shredPref = getSharedPreferences("pref", MODE_PRIVATE)
//        shredPref.edit().putString("email", email).apply()
//    }
//    private fun getEmailFromPreferences(): String?{
//        val shredPref = getSharedPreferences("pref", MODE_PRIVATE)
//        return shredPref.getString("email", null)
//    }
//}
//
