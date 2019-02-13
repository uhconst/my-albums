package com.uhc.my_albums.di.modules

import com.uhc.my_albums.ui.list.AlbumsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAlbumsActivity(): AlbumsActivity
}