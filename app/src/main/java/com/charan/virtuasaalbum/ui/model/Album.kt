package com.charan.virtuasaalbum.ui.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
    tableName = "album"
)
data class Album(

    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @Json(name = "userId")
    @ColumnInfo(name = "userId")
    val userId: Int?,

    @Json(name = "title")
    @ColumnInfo(name = "title")
    val title: String
) : Serializable