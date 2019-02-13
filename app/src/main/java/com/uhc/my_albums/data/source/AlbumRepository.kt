package com.uhc.my_albums.data.source

import android.util.Log
import com.uhc.my_albums.data.Album
import com.uhc.my_albums.data.source.local.AlbumsDao
import com.uhc.my_albums.data.source.remote.ApiInterface
import com.uhc.my_albums.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class AlbumRepository @Inject constructor(
    val apiInterface: ApiInterface,
    val albumsDao: AlbumsDao, val utils: Utils
) {

    fun getAlbums(limit: Int, offset: Int): Observable<List<Album>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Album>>? = null
        if (hasConnection) {
            observableFromApi = getAlbumsFromApi()
        }
        val observableFromDb = getAlbumsFromDb(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    fun getAlbumsFromApi(): Observable<List<Album>> {
        return apiInterface.getAlbums()
            .doOnNext {
                Log.e("REPOSITORY API * ", it.size.toString())
                for (item in it) {
                    albumsDao.insertAlbum(item)
                }
            }
    }

    fun getAlbumsFromDb(limit: Int, offset: Int): Observable<List<Album>> {
        return albumsDao.queryAlbums(limit, offset)
            .toObservable()
            .doOnNext {
                //Print log it.size :)
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }
}