package com.uhc.my_albums.di.component

import com.uhc.my_albums.AlbumApplication
import com.uhc.my_albums.di.modules.AppModule
import com.uhc.my_albums.di.modules.BuildersModule
import com.uhc.my_albums.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class, BuildersModule::class, AppModule::class,
        NetModule::class
    )
)
interface AppComponent {
    fun inject(app: AlbumApplication)
}