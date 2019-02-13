package com.uhc.my_albums.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.uhc.my_albums.data.Album
import com.uhc.my_albums.data.source.AlbumRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject


class AlbumsViewModel @Inject constructor(
    private val abumRepository: AlbumRepository
) : ViewModel() {

    var albumsResult: MutableLiveData<List<Album>> = MutableLiveData()
    var albumsError: MutableLiveData<String> = MutableLiveData()
    var albumsLoader: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<Album>>

    fun albumsResult(): LiveData<List<Album>> {
        return albumsResult
    }

    fun albumsError(): LiveData<String> {
        return albumsError
    }

    fun albumsLoader(): LiveData<Boolean> {
        return albumsLoader
    }

    fun loadAlbums(limit: Int, offset: Int) {

        disposableObserver = object : DisposableObserver<List<Album>>() {
            override fun onComplete() {

            }

            override fun onNext(albums: List<Album>) {
                albumsResult.postValue(albums)
                albumsLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                albumsError.postValue(e.message)
                albumsLoader.postValue(false)
            }
        }

        abumRepository.getAlbums(limit, offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, MILLISECONDS)
            .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if (!disposableObserver.isDisposed) disposableObserver.dispose()
    }

}