package com.example.apk_kelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Sepatu
import kotlinx.android.synthetic.main.activity_edit_sepatu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditSepatuActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sepatu)
        setupListener()
    }

    fun setupListener(){
        btn_saveSepatu.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.SepatuDao().addSepatu(
                    Sepatu(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }
}