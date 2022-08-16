package com.chi.test.lvl2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TestName")
data class ModelStudent (
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "isStudent")
    var isStudent: Boolean
    )