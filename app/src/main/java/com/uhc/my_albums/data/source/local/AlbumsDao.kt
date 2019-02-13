package com.uhc.my_albums.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.uhc.my_albums.data.Album
import io.reactivex.Single

@Dao
interface AlbumsDao {

  @Query("SELECT * FROM albums ORDER BY title limit :limit offset :offset")
  fun queryAlbums(limit:Int, offset:Int): Single<List<Album>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAlbum(album: Album)

  @Insert(
      onConflict = OnConflictStrategy.REPLACE
  )
  fun insertAllAlbums(albums: List<Album>)
}