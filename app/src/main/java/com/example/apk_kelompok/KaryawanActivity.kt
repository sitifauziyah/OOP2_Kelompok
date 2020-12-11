package com.example.apk_kelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apk_kelompok.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_karyawan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KaryawanActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_karyawan)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allKaryawan = db.KaryawanDao().getAllKaryawan()
            Log.d("KaryawanActivity", "dbResponse: $allKaryawan")
        }
    }

    fun setupListener() {
        btn_createKaryawan.setOnClickListener {
            startActivity(Intent(this, EditKaryawanActivity::class.java))
        }
    }

}