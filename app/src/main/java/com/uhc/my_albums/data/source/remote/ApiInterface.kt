package com.uhc.my_albums.data.source.remote

import com.uhc.my_albums.data.Album
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

  @GET("albums/")
  fun getAlbums(): Observable<List<Album>>
}