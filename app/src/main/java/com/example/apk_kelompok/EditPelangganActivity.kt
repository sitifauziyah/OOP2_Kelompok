package com.example.apk_kelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Karyawan
import com.example.apk_kelompok.Database.Pelanggan
import kotlinx.android.synthetic.main.activity_edit_pelanggan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPelangganActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pelanggan)
        setupListener()
    }

    fun setupListener(){
        btn_savePelanggan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PelangganDao().addPelanggan(
                    Pelanggan(0, txt_nama.text.toString(), txt_nohp.text.toString())
                )
                finish()
            }
        }
    }
}