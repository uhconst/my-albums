package com.uhc.my_albums.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
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

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Change the table name to the correct one
                database.execSQL("ALTER TABLE album RENAME TO albums")
            }
        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAlbumsDatabase(app: Application): Database = Room.databaseBuilder(
        app,
        Database::class.java, Constants.DATABASE_NAME
    )
        /*.addMigrations(MIGRATION_1_2)*/
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