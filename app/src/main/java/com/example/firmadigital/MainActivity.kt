package com.example.firmadigital

import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnQRActivity.setOnClickListener (this)
        btnCameraActivity.setOnClickListener (this)
        btnFirmaActivity.setOnClickListener (this)
        btnGoogleMap.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        val intentQR = Intent(this,QrActivitie::class.java)
        val intentCamera = Intent(this,CameraActivity::class.java)
        val intentFirma = Intent(this,QrActivitie::class.java)
        val intentMap = Intent(this,MapsActivity::class.java)

        when(v?.id) {
            R.id.btnQRActivity ->{
                startActivity(intentQR)
                finish()
            }
            R.id.btnCameraActivity ->{
                startActivity(intentCamera)
                finish()
            }
            R.id.btnFirmaActivity ->{
                startActivity(intentFirma)
                finish()
            }
            R.id.btnGoogleMap ->{
                startActivity(intentMap)
                finish()
            }
            else ->{
                print("hola")
            }
        }
    }

}