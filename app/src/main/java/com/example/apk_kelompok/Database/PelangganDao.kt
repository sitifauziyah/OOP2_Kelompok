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
}