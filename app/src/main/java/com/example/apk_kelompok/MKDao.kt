package com.example.apk_kelompok
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao

interface MKDao {
    @Query("SELECT * FROM mk")
    fun getAll(): List<MK>

    @Query("SELECT * FROM mk WHERE kode IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<MK>

    @Query("SELECT * FROM mk WHERE nama LIKE :nama AND " +
            "sks LIKE :sks LIMIT 1")
    fun findByName(nama: String, sks: String): MK

    @Insert
    fun insertAll(vararg mk: MK)

    @Delete
    fun delete(mk: MK)
}