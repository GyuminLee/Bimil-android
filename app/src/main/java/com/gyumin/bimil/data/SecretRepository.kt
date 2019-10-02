package com.gyumin.bimil.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.lang.Exception

class SecretRepository(private val secretDao: SecretDao) {
    val allSecrets: LiveData<List<Secret>> = secretDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(secret: Secret) {
        secretDao.insert(secret)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(secret: Secret) {
        secretDao.delete(secret)
    }



//    private val secretDatabase = SecretDatabase.getInstance(application)!!
//    private val secretDao: SecretDao = secretDatabase.secretDao()
//    private val secrets: LiveData<List<Secret>> = secretDao.getAll()
//
//    fun getAll(): LiveData<List<Secret>> {
//        return secrets
//    }
//
//    suspend fun insert(secret: Secret) {
//        secretDao.insert(secret)
//    }
//
//    suspend fun delete(secret: Secret) {
//        secretDao.delete(secret)
//    }
}