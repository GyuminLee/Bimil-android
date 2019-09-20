package com.gyumin.bimil.bimil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gyumin.bimil.data.Secret
import com.gyumin.bimil.data.SecretRepository

class SecretViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SecretRepository(application)
    private val secrets = repository.getAll()

    fun getAll(): LiveData<List<Secret>> {
       return this.secrets
    }

    fun insert(secret: Secret) {
        repository.insert(secret)
    }

    fun delete(secret: Secret) {
        repository.delete(secret)
    }
}