package com.example.apk_kelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Constant
import com.example.apk_kelompok.Database.Sepatu
import kotlinx.android.synthetic.main.activity_sepatu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SepatuActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var sepatuAdapter: SepatuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepatu)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadSepatu()
    }
    fun loadSepatu(){
        CoroutineScope(Dispatchers.IO).launch {
            val allSepatu = db.SepatuDao().getAllSepatu()
            Log.d("SepatuActivity", "dbResponse: $allSepatu")
            withContext(Dispatchers.Main) {
                sepatuAdapter.setData(allSepatu)
            }
        }

    }

    fun setupListener() {
        btn_createSepatu.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        sepatuAdapter = SepatuAdapter(arrayListOf(), object: SepatuAdapter.OnAdapterListener {
            override fun onClick(sepatu: Sepatu) {
                intentEdit(sepatu.id, Constant.TYPE_READ)
            }
            override fun onDelete(sepatu: Sepatu) {
                deleteDialog(sepatu)
            }
        })
        list_sepatu.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = sepatuAdapter
        }
    }

    fun intentEdit(helmId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditSepatuActivity::class.java)
                .putExtra("intent_id", helmId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(sepatu: Sepatu) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.SepatuDao().deleteSepatu(sepatu)
                    loadSepatu()
                }
            }
        }
        alertDialog.show()
    }
}