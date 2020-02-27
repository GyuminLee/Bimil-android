package com.gyumin.bimil.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gyumin.bimil.domain.Secret


@Dao

interface SecretDao {

    @Query("SELECT * FROM secret ORDER BY id ASC")
    fun getAll() : LiveData<List<Secret>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(secret: Secret)

    @Delete
    fun delete(secret: Secret)

    @Query("SELECT * FROM secret where name like :queryMsg")
    fun getSearchResult(queryMsg : String) : LiveData<List<Secret>>



}