package com.uhc.my_albums.ui.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject


class AlbumsViewModelFactory @Inject constructor(
    private val albumsViewModel: AlbumsViewModel
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
      return albumsViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}