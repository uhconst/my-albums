package com.uhc.my_albums.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
    tableName = "albums"
)
data class Album(

    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @Json(name = "userId")
    @ColumnInfo(name = "userId")
    val userId: Long?,

    @Json(name = "title")
    @ColumnInfo(name = "title")
    val title: String
) : Serializable