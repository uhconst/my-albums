package com.uhc.my_albums.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.uhc.my_albums.data.source.local.AlbumsDao
import com.uhc.my_albums.data.source.local.Database
import com.uhc.my_albums.ui.list.AlbumsViewModelFactory
import com.uhc.my_albums.utils.Constants
import com.uhc.my_albums.utils.Utils
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
    fun provideAlbumsDatabase(app: Application): Database = Room.databaseBuilder(
        app,
        Database::class.java, Constants.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideAlbumsDao(
        database: Database
    ): AlbumsDao = database.albumsDao()

    @Provides
    @Singleton
    fun provideAlbumsViewModelFactory(
        factory: AlbumsViewModelFactory
    ): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}