package com.example.apk_kelompok

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MK::class), version = 1)
abstract class MKRoomDatabase {
    abstract fun MKDao(): MKDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        MKRoomDatabase::class.java, "DBMHS"
    ).build()


}