package com.gyumin.bimil.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "secret")

data class Secret(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "typeNumber")
    var typeNumber: Boolean,

    @ColumnInfo(name = "typeCapital")
    var typeCapital: Boolean,

    @ColumnInfo(name = "typeSpecialCharacter")
    var typeSpecialCharacter: Boolean,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "note")
    var note: String
) {
    constructor() : this(null, "", false, false, false, "", "")
}

