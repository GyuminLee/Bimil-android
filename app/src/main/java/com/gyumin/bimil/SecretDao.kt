package com.gyumin.bimil

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao

interface SecretDao {

    @Query("SELECT * FROM secret ORDER BY id ASC")
    fun getAll() : LiveData<List<Secret>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(secret: Secret)

    @Delete
    fun delete(secret: Secret)

}