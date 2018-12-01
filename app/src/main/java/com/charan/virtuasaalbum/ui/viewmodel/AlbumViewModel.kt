package com.charan.virtuasaalbum.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.charan.virtuasaalbum.ui.model.Album
import com.charan.virtuasaalbum.ui.repository.AlbumRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject


class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel() {

  var albumResult: MutableLiveData<List<Album>> = MutableLiveData()
  var albumError: MutableLiveData<String> = MutableLiveData()
  var albumLoader: MutableLiveData<Boolean> = MutableLiveData()
  lateinit var disposableObserver: DisposableObserver<List<Album>>

  fun albumResult(): LiveData<List<Album>> {
    return albumResult
  }

  fun albumError(): LiveData<String> {
    return albumError
  }

  fun albumLoader(): LiveData<Boolean> {
    return albumLoader
  }

  fun loadAlbum(limit: Int, offset: Int ) {

      albumLoader.postValue(true)

    disposableObserver = object : DisposableObserver<List<Album>>() {
      override fun onComplete() {

      }

      override fun onNext(listAlbum: List<Album>) {
        albumResult.postValue(listAlbum)
        albumLoader.postValue(false)
      }

      override fun onError(e: Throwable) {
        albumError.postValue(e.message)
        albumLoader.postValue(false)
      }
    }

    albumRepository.getAlbum(limit, offset)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .debounce(400, MILLISECONDS)
        .subscribe(disposableObserver)
  }

  fun disposeElements(){
    if(null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
  }

}