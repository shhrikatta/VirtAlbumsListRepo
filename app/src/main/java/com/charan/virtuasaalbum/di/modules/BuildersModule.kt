package com.charan.virtuasaalbum.di.modules

import com.charan.virtuasaalbum.ui.view.AlbumActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeAlbumActivity(): AlbumActivity
}