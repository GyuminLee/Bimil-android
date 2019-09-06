package com.gyumin.bimil

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

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