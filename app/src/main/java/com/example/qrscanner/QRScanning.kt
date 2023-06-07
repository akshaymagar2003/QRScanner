package com.example.qrscanner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrscanner.databinding.ActivityQrScanningBinding
import com.google.firebase.firestore.FirebaseFirestore


class QRScanning : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityQrScanningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQrScanningBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref=this?.getPreferences(Context.MODE_PRIVATE)?:return
        val isLogin=sharedPref.getString("Email","1")
//        binding.logout.setOnClickListener {
//            sharedPref.edit().remove("Email").apply()
//            var intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        if(isLogin=="1")
        {
            var email=intent.getStringExtra("email")
            if(email!=null)
            {

                with(sharedPref.edit())
                {
                    putString("Email",email)
                    apply()
                }
            }
            else{
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else{




        }




    }


//    private fun setText(email:String?)
//    {
//        db= FirebaseFirestore.getInstance()
//        if (email != null) {
//            db.collection("USERS").document(email).get()
//                .addOnSuccessListener {
//                        tasks->
//
//                }
//        }
//
//    }
}