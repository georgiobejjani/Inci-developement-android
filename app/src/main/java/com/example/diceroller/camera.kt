package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

private const val REQUEST_CODE = 123
private const val IMAGE_PICK_CODE = 100

class camera : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        var capture: Button = findViewById<Button>(R.id.pic)
        var upload: Button = findViewById<Button>(R.id.galery)


        capture.setOnClickListener {
            var intent = Intent (MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE)
        }
        upload.setOnClickListener {
            uploadImageGallery()
        }
    }


    private fun uploadImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        val imageView = findViewById<ImageView>(R.id.picture1)


        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(takenImage)
        }


        else if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
        }


        else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}