package com.gyumin.bimil.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.gyumin.bimil.data.Secret
import com.gyumin.bimil.data.SecretDao

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

    @WorkerThread
    fun search(queryMsg : String) : LiveData<List<Secret>> {
        return secretDao.getSearchResult(queryMsg)
    }

}