package com.gyumin.bimil.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Secret::class], version = 2)

abstract class SecretDatabase: RoomDatabase() {
    abstract fun secretDao(): SecretDao

    companion object {
        private var instance: SecretDatabase? = null

        fun getInstance(context: Context): SecretDatabase? {
            if(instance == null) {
                synchronized(SecretDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        SecretDatabase::class.java, "secret")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}