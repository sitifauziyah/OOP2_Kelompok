package com.example.apk_kelompok

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mahasiswa::class), version = 1)
abstract class MahasiswaRoomDatabase {
    abstract fun mahasiswaDao(): MahasiswaDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        MahasiswaRoomDatabase::class.java, "DBMHS"
    ).build()


}