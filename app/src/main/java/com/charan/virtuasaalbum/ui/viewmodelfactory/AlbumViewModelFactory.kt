package com.charan.virtuasaalbum.ui.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.charan.virtuasaalbum.ui.viewmodel.AlbumViewModel
import javax.inject.Inject


class AlbumViewModelFactory @Inject constructor(
    private val albumViewModel: AlbumViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(AlbumViewModel::class.java!!)) {
      return albumViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}