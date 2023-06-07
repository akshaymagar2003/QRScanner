package com.example.qrscanner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrscanner.databinding.ActivityQrScanningBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


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

        binding.scanButton.setOnClickListener {
            // Initiate QR code scan
            initiateScan()
        }




    }
    private fun initiateScan() {
        val integrator = IntentIntegrator(this@QRScanning)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan a QR Code")
        integrator.setCameraId(0) // Use the rear camera
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.setOrientationLocked(false)
        integrator.captureActivity = CaptureActivityVertical::class.java

        integrator.initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null && result.contents != null) {
            val decodedValue = result.contents
            binding.decodedTextView.text = decodedValue
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}