package com.example.apk_kelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apk_kelompok.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_pelanggan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PelangganActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelanggan)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allPelanggan = db.PelangganDao().getAllPelanggan()
            Log.d("PelangganActivity", "dbResponse: $allPelanggan")
        }
    }

    fun setupListener() {
        btn_createPelanggan.setOnClickListener {
            startActivity(Intent(this, EditKaryawanActivity::class.java))
        }
    }

}