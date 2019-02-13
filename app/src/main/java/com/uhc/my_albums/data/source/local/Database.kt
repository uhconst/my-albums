package com.uhc.my_albums.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.uhc.my_albums.data.Album

@Database(entities = arrayOf(Album::class), version = 8)
abstract class Database : RoomDatabase() {
  abstract fun albumsDao(): AlbumsDao
}