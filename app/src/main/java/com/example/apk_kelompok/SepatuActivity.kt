package com.example.apk_kelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apk_kelompok.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_sepatu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SepatuActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepatu)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allSepatu = db.SepatuDao().getAllSepatu()
            Log.d("SepatuActivity", "dbResponse: $allSepatu")
        }
    }

    fun setupListener() {
        btn_createSepatu.setOnClickListener {
            startActivity(Intent(this, EditSepatuActivity::class.java))
        }
    }
}