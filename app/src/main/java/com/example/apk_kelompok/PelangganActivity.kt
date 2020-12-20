package com.example.apk_kelompok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apk_kelompok.Database.AppRoomDB
import com.example.apk_kelompok.Database.Constant
import com.example.apk_kelompok.Database.Pelanggan
import com.example.apk_kelompok.Database.Sepatu
import kotlinx.android.synthetic.main.activity_pelanggan.*
import kotlinx.android.synthetic.main.activity_sepatu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PelangganActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var pelangganAdapter: PelangganAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelanggan)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadPelanggan()

    }

    fun loadPelanggan() {
        CoroutineScope(Dispatchers.IO).launch {
            val allPelanggan = db.PelangganDao().getAllPelanggan()
            Log.d("PelangganActivity", "dbResponse: $allPelanggan")
            withContext(Dispatchers.Main) {
                pelangganAdapter.setData(allPelanggan)
            }
        }
    }

    fun setupListener() {
        btn_createPelanggan.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        pelangganAdapter = PelangganAdapter(arrayListOf(), object: PelangganAdapter.OnAdapterListener {
            override fun onClick(pelanggan: Pelanggan) {
                intentEdit(pelanggan.id, Constant.TYPE_READ)
            }

            override fun onDelete(pelanggan: Pelanggan) {
                deleteDialog(pelanggan)
            }

        })
        list_pelanggan.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = pelangganAdapter
        }
    }

    fun intentEdit(pelangganId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditPelangganActivity::class.java)
                .putExtra("intent_id", pelangganId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(pelanggan: Pelanggan) {
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
                    db.PelangganDao().deletePelanggan(pelanggan)
                    loadPelanggan()
                }
            }
        }
        alertDialog.show()
    }
}