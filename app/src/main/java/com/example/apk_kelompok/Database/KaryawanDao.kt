package com.example.apk_kelompok.Database

import androidx.room.*

@Dao
interface KaryawanDao {
    @Insert
    suspend fun addKaryawan(karyawan: Karyawan)

    @Update
    suspend fun updateKaryawan(karyawan: Karyawan)

    @Delete
    suspend fun deleteKaryawan(karyawan: Karyawan)

    @Query("SELECT * FROM karyawan")
    suspend fun getAllKaryawan(): List<Karyawan>

    @Query("SELECT * FROM karyawan WHERE id=:karyawan_id")
    suspend fun getKaryawan(karyawan_id: Int) : List<Karyawan>
}