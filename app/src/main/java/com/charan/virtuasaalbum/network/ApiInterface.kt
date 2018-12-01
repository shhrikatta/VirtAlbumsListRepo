package com.charan.virtuasaalbum.network

import com.charan.virtuasaalbum.ui.model.Album
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

  @GET("albums")
  fun getAlbumList(@Query("start") start: String): Observable<List<Album>>
}