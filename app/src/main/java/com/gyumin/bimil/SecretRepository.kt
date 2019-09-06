package com.gyumin.bimil

import android.app.Application
import android.arch.lifecycle.LiveData
import java.lang.Exception

class SecretRepository(application: Application) {

    private val secretDatabase = SecretDatabase.getInstance(application)!!
    private val secretDao: SecretDao = secretDatabase.secretDao()
    private val secrets: LiveData<List<Secret>> = secretDao.getAll()

    fun getAll(): LiveData<List<Secret>> {
        return secrets
    }

    fun insert(secret: Secret) {
        try{
            val thread = Thread(Runnable {
                secretDao.insert(secret)
            })
            thread.start()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }

    fun delete(secret: Secret) {
        try {
            val thread = Thread(Runnable {
                secretDao.delete(secret)
            })
            thread.start()
        } catch (e: Exception) {
            System.out.println(e)
        }
    }
}