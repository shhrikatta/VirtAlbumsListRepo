package com.charan.virtuasaalbum.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import com.charan.virtuasaalbum.db.AlbumDao
import com.charan.virtuasaalbum.db.Database
import com.charan.virtuasaalbum.ui.viewmodelfactory.AlbumViewModelFactory
import com.charan.virtuasaalbum.utils.Constants
import com.charan.virtuasaalbum.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: Application) {

  @Provides
  @Singleton
  fun provideApplication(): Application = app

  @Provides
  @Singleton
  fun provideAlbumsDatabase(app: Application): Database = Room.databaseBuilder(app,
      Database::class.java, Constants.DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()

  @Provides
  @Singleton
  fun provideAlbumsDao(
      database: Database): AlbumDao = database.albumDao()

  @Provides
  @Singleton
  fun provideAlbumsViewModelFactory(
      factory: AlbumViewModelFactory): ViewModelProvider.Factory = factory

  @Provides
  @Singleton
  fun provideUtils(): Utils = Utils(app)
}