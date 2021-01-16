package com.example.apk_kelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Constant
import com.example.apk_kelompok.Database.Pelanggan
import kotlinx.android.synthetic.main.activity_edit_pelanggan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPelangganActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var pelangganId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pelanggan)
        setupListener()
        setupView()
    }

    fun setupListener(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        btn_savePelanggan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PelangganDao().addPelanggan(
                    Pelanggan(0, txt_nama.text.toString(), txt_nohp.text.toString())
                )
                finish()
            }
        }
        btn_updatePelanggan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.PelangganDao().updatePelanggan(
                    Pelanggan(pelangganId, txt_nama.text.toString(), txt_nohp.text.toString())
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
                btn_updatePelanggan.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_savePelanggan.visibility = View.GONE
                btn_updatePelanggan.visibility = View.GONE
                getPelanggan()
            }
            Constant.TYPE_UPDATE -> {
                btn_savePelanggan.visibility = View.GONE
                getPelanggan()
            }
        }
    }

    fun getPelanggan() {
        pelangganId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val pelanggans =  db.PelangganDao().getPelanggan( pelangganId )[0]
            txt_nama.setText( pelanggans.nama )
            txt_nohp.setText( pelanggans.nohp )
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}