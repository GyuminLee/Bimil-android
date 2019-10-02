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

}