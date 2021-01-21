package com.example.firmadigital

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File


private val REQUEST_CODE = 42

//la imagen traida directamente es low quality, hay que leerla directo desde el archivo generado
//high quality
private lateinit var photoFile: File
private val FILE_NAME = "photo.jpg"

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnTakeAPicture.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            //high quality
            photoFile = getPhotoFile(FILE_NAME)
            var fileProvider = FileProvider.getUriForFile(this,"com.example.firmadigital.fileprovider",
                photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider)

            //aca empieza la activity, antes se asigna el photoFile
            startActivityForResult(takePictureIntent, REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode   == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //val takenImage =  data?.extras?.get("data") as Bitmap
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            ivTakeImage.setImageBitmap(takenImage)
        }else{
            super.onActivityResult(requestCode, resultCode, data)

        }
    }

    private fun getPhotoFile(fileName: String) : File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storageDirectory)
    }
}