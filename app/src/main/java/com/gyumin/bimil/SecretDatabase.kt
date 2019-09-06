package com.gyumin.bimil

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Secret::class], version = 1)

abstract class SecretDatabase: RoomDatabase() {
    abstract  fun secretDao(): SecretDao

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