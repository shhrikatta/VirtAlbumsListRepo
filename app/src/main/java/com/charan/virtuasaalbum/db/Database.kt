package com.charan.virtuasaalbum.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.charan.virtuasaalbum.ui.model.Album

@Database(entities = arrayOf(Album::class), version = 8)
abstract class Database : RoomDatabase() {
  abstract fun albumDao(): AlbumDao
}