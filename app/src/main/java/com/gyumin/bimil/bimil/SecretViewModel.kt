package com.gyumin.bimil.bimil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import com.gyumin.bimil.data.Secret
import com.gyumin.bimil.data.SecretDatabase
import com.gyumin.bimil.data.SecretRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


class SecretViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    // By default all the coroutines launched in this scope should be using the Main dispatcher
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: SecretRepository
    val allSecrets: LiveData<List<Secret>>

    init {
        val secretDao = SecretDatabase.getDatabase(application, scope).secretDao()
        repository = SecretRepository(secretDao)
        allSecrets = repository.allSecrets
    }

    fun insert(secret: Secret) = scope.launch(Dispatchers.IO) {
        repository.insert(secret)
    }

    fun delete(secret: Secret) = scope.launch(Dispatchers.IO) {
        repository.delete(secret)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}