package com.example.apk_kelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Karyawan
import kotlinx.android.synthetic.main.activity_edit_karyawan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditKaryawanActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_karyawan)
        setupListener()
    }

    fun setupListener(){
        btn_saveKaryawan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.KaryawanDao().addKaryawan(
                    Karyawan(0, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
    }
}