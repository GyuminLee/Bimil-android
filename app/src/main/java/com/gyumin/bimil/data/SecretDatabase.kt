package com.gyumin.bimil.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Secret::class], version = 2)
abstract class SecretDatabase : RoomDatabase() {
    abstract fun secretDao(): SecretDao

    companion object{
        //Volatile : store in Main Memory (I am gonna use multithread, variable could have different value
        @Volatile
        private var INSTANCE: SecretDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): SecretDatabase {
            //Singleton
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SecretDatabase::class.java,
                    "secret"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
