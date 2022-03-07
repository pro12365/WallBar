package com.example.wallBar.ui

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wallBar.R
import com.example.wallBar.databinding.ActivityClickedWallpaperBinding
import com.google.firebase.inject.Deferred
import kotlinx.coroutines.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.*

class ClickedWallpaper : AppCompatActivity() {
    lateinit var binding: ActivityClickedWallpaperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityClickedWallpaperBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val url = intent.getStringExtra("link")
        val urlimage = URL(url)
        Glide.with(this).load(url).into(binding.llk)
        fun saveImage(image: Bitmap?) {

            val random1 = Random().nextInt(520985)
            val random2 = Random().nextInt(520985)

            val name = "Wallbar-${random1 + random2}"

            val data: OutputStream
            try {
                val resolver = contentResolver
                val contentValues = ContentValues()
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "Wallbar Wallpaper"
                )
                val imageUri =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                data = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
                image?.compress(Bitmap.CompressFormat.JPEG, 100, data)
                Objects.requireNonNull<OutputStream?>(data)
                Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                Toast.makeText(this, "Image Not Save", Toast.LENGTH_SHORT).show()
            }

        }

        fun URL.toBitmap(): Bitmap? {
            return try {
                BitmapFactory.decodeStream(openStream())
            } catch (e: IOException) {
                null
            }

        }

        fun download(view: View) {

        }
        binding.butset.setOnClickListener {
            Toast.makeText(this, "Wallpaper Set", Toast.LENGTH_SHORT).show()
            val result: kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlimage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }
            }

            binding.butshare.setOnClickListener {
                //Code Needed To be Written
            }
            binding.download.setOnClickListener {
                val result: kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                    urlimage.toBitmap()
                }

                GlobalScope.launch(Dispatchers.Main) {
                    saveImage(result.await())
                }
            }
        }
    }