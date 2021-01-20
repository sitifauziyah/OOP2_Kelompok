package com.example.apk_kelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Constant
import com.example.apk_kelompok.Database.Karyawan
import kotlinx.android.synthetic.main.activity_edit_karyawan.*
import kotlinx.android.synthetic.main.activity_edit_karyawan.txt_nama
import kotlinx.android.synthetic.main.activity_edit_pelanggan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditKaryawanActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var karyawanId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_karyawan)
        setupListener()
        setupView()
    }

    fun setupListener(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        btn_saveKaryawan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.KaryawanDao().addKaryawan(
                    Karyawan(0, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
        btn_updateKaryawan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.KaryawanDao().updateKaryawan(
                    Karyawan(karyawanId, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_updateKaryawan.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_saveKaryawan.visibility = View.GONE
                btn_updateKaryawan.visibility = View.GONE
                getKaryawan()
            }
            Constant.TYPE_UPDATE -> {
                btn_saveKaryawan.visibility = View.GONE
                getKaryawan()
            }
        }
    }

    fun getKaryawan() {
        karyawanId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val karyawann =  db.KaryawanDao().getKaryawan( karyawanId )[0]
            txt_nama.setText( karyawann.nama )
            txt_username.setText( karyawann.username )
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}