package com.gyumin.bimil

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "secret")

data class Secret(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "note")
    var note: String,

    @ColumnInfo(name = "address")
    var address: String
) {
    constructor() : this(null, "", "", "", "")
}

