package com.example.apk_kelompok.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "karyawan")
data class Karyawan(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "username") val username: String
)