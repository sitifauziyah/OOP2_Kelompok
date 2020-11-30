package com.example.apk_kelompok

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mk")
data class MK (
    @PrimaryKey val kode: Int,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "sks") val sks: String?
)