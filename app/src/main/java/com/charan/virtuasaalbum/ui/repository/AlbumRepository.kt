package com.charan.virtuasaalbum.ui.repository

import android.util.Log
import com.charan.virtuasaalbum.db.AlbumDao
import com.charan.virtuasaalbum.network.ApiInterface
import com.charan.virtuasaalbum.ui.model.Album
import com.charan.virtuasaalbum.utils.Constants
import com.charan.virtuasaalbum.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class AlbumRepository @Inject constructor(val apiInterface: ApiInterface,
                                          val albumDao: AlbumDao, val utils: Utils
) {

  fun getAlbumDetails(id: Int): Observable<Album> {
    val hasConnection = utils.isConnectedToInternet()
      var observableFromApi: Observable<Album> = Observable.just(Album(0, 0, ""))
      if (hasConnection){
      observableFromApi = getAlbumDetailsFromApi(id)
    }

    return observableFromApi
  }

  fun getAlbum(limit: Int, offset: Int): Observable<List<Album>> {
    val hasConnection = utils.isConnectedToInternet()
    var observableFromApi: Observable<List<Album>>? = null
    if (hasConnection){
      observableFromApi = getAlbumFromApi()
    }
    val observableFromDb = getAlbumFromDb(limit, offset)

    return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
    else observableFromDb
  }

  fun getAlbumDetailsFromApi(id: Int): Observable<Album> {
    return apiInterface.getAlbumDetails(id)
        .doOnNext {
        }
  }

  fun getAlbumFromApi(): Observable<List<Album>> {
    return apiInterface.getAlbumList(Constants.START_ZERO_VALUE)
        .doOnNext {
          Log.e("REPOSITORY API * ", it.size.toString())
          for (item in it) {
            albumDao.insertAlbum(item)
          }
        }
  }

  fun getAlbumFromDb(limit: Int, offset: Int): Observable<List<Album>> {
      return albumDao.queryAlbum(limit, offset)
        .toObservable()
        .doOnNext {
          //Print log it.size :)
          Log.e("REPOSITORY DB *** ", it.size.toString())
        }
  }
}