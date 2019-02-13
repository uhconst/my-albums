package com.uhc.my_albums

import android.app.Activity
import android.app.Application
import com.uhc.my_albums.di.component.DaggerAppComponent
import com.uhc.my_albums.di.modules.AppModule
import com.uhc.my_albums.di.modules.NetModule
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