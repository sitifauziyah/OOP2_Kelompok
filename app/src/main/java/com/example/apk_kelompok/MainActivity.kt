package com.example.apk_kelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_sepatu.setOnClickListener{
            val intent = Intent(this,SepatuActivity::class.java)
            startActivity(intent)
        }

        btn_pelanggan.setOnClickListener{
            val intent = Intent(this, PelangganActivity::class.java)
            startActivity(intent)
        }
        btn_karyawan.setOnClickListener{
            val intent = Intent(this, KaryawanActivity::class.java)
            startActivity(intent)
        }
    }
}