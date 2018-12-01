package com.charan.virtuasaalbum.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.charan.virtuasaalbum.ui.model.Album
import io.reactivex.Single

@Dao
interface AlbumDao {

  @Query("SELECT * FROM album ORDER BY title limit :limit offset :offset")
  fun queryAlbum(limit:Int, offset:Int): Single<List<Album>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAlbum(album: Album)

  @Insert(
      onConflict = OnConflictStrategy.REPLACE
  )
  fun insertAllAlbums(listAlbum: List<Album>)
}