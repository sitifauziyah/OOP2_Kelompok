package com.example.apk_kelompok.Database

import androidx.room.*

@Dao
interface PelangganDao {
    @Insert
    suspend fun addPelanggan(pelanggan: Pelanggan)

    @Update
    suspend fun updatePelanggan(pelanggan: Pelanggan)

    @Delete
    suspend fun deletePelanggan(pelanggan: Pelanggan)

    @Query("SELECT * FROM pelanggan")
    suspend fun getAllPelanggan(): List<Pelanggan>

    @Query("SELECT * FROM pelanggan WHERE id=:pelanggan_id")
    suspend fun getPelanggan(pelanggan_id: Int) : List<Pelanggan>
}