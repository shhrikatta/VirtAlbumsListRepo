package com.charan.virtuasaalbum

import android.app.Activity
import android.app.Application
import com.charan.virtuasaalbum.di.component.DaggerAppComponent
import com.charan.virtuasaalbum.di.modules.AppModule
import com.charan.virtuasaalbum.di.modules.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class AlbumApplication: Application(), HasActivityInjector {

  @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .netModule(NetModule(BuildConfig.URL))
        .build().inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}