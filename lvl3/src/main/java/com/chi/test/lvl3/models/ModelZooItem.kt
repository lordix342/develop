package com.chi.test.lvl3.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ZooItem")
data class ModelZooItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "diet")
    @SerializedName("diet")
    val diet: String,
    @ColumnInfo(name = "image")
    @SerializedName("image_link")
    val imageLink: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)