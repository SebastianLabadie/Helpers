package com.example.firmadigital

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.ActivityChooserView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.activity_qr_activitie.*


private const val CAMERA_REQUEST_CODE = 101

class QrActivitie : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_activitie)


        setupPermissions()
        codeScanner()
    }

    private fun codeScanner(){
        codeScanner = CodeScanner(this,scanner_view)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    textView2.text = it.text
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Camera initialization error:${it.message} ")
                }
            }

            scanner_view.setOnClickListener {
                codeScanner.startPreview()

            }

        }
    }

    //cada ves que salgo y entro de la app
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    //liberar recursos al salir de la app
    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    //Checkear los permisos
    private fun setupPermissions(){
        val permissions = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)

        if (permissions != PackageManager.PERMISSION_GRANTED){
            requestPermissions()
        }


    }

    //pedir permisos
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    //resultados de pedir permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if ( grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"You need a camera permission",Toast.LENGTH_SHORT)
                }else{

                }
            }
        }
    }
}