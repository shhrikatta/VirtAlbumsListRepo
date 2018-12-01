package com.charan.virtuasaalbum.di.component

import com.charan.virtuasaalbum.AlbumApplication
import com.charan.virtuasaalbum.di.modules.AppModule
import com.charan.virtuasaalbum.di.modules.BuildersModule
import com.charan.virtuasaalbum.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class,
        NetModule::class)
)
interface AppComponent {
  fun inject(app: AlbumApplication)
}