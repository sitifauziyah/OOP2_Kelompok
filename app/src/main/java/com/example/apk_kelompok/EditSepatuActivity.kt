package com.example.apk_kelompok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Constant
import com.example.apk_kelompok.Database.Sepatu
import kotlinx.android.synthetic.main.activity_edit_sepatu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditSepatuActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var sepatuId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sepatu)
        setupListener()
        setupView()
    }

    fun setupListener(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        btn_saveSepatu.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.SepatuDao().addSepatu(
                    Sepatu(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
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

            }
            Constant.TYPE_READ -> {
                btn_saveSepatu.visibility = View.GONE
                getSepatu()
            }
        }
    }

    fun getSepatu() {
        sepatuId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val sepatus =  db.SepatuDao().getSepatu( sepatuId )[0]
            txt_merk.setText( sepatus.merk )
            txt_stok.setText( sepatus.harga )
            txt_harga.setText( sepatus.stok )
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}