package com.gyumin.bimil

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gyumin.bimil.data.Secret
import com.gyumin.bimil.data.SecretDao
import com.gyumin.bimil.data.SecretDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class SecretDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var secretDao: SecretDao
    private lateinit var db: SecretDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, SecretDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        secretDao = db.secretDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun getAllSecrets() {
        val secret1 = Secret(
            null,
            "Test1",
            false,
            true,
            false,
            "www.test.com",
            "Google"
        )
        secretDao.insert(secret1)
        val secret2 = Secret(
            null,
            "Test2",
            true,
            true,
            true,
            "www.test1.com",
            "ID : google"
        )
        secretDao.insert(secret2)
        val allSecrets = secretDao.getAll().waitForValue()
        assertEquals(allSecrets[0].name, secret1.name)
        assertEquals(allSecrets[1].name, secret2.name)
    }


    @Test
    @Throws(Exception::class)
    fun delete() {
        val secret1 = Secret(
            null,
            "Test1",
            false,
            true,
            false,
            "www.test.com",
            "Google"
        )
        secretDao.insert(secret1)
        var allSecrets = secretDao.getAll().waitForValue()

        secretDao.delete(allSecrets[0])
        allSecrets = secretDao.getAll().waitForValue()
        assertTrue(allSecrets.isEmpty())
    }


}