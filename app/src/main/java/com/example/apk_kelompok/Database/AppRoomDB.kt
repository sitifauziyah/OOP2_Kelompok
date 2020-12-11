package com.example.apk_kelompok.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Sepatu::class, Karyawan::class, Pelanggan::class), version = 1)

abstract class AppRoomDB : RoomDatabase() {

    abstract fun SepatuDao(): SepatuDao
    abstract fun KaryawanDao(): KaryawanDao
    abstract fun PelangganDao(): PelangganDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppRoomDB::class.java,
            "APPDB"
        ).build()

    }
}