package com.example.apk_kelompok.Database

import androidx.room.*

@Dao
interface SepatuDao {
    @Insert
    suspend fun addSepatu(sepatu: Sepatu)

    @Update
    suspend fun updateSepatu(sepatu: Sepatu)

    @Delete
    suspend fun deleteSepatu(sepatu: Sepatu)

    @Query("SELECT * FROM sepatu")
    suspend fun getAllSepatu(): List<Sepatu>

    @Query("SELECT * FROM sepatu WHERE id=:Sepatu_id")
    suspend fun getSepatu(Sepatu_id: Int) : List<Sepatu>
}