package com.uhc.my_albums.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


class Utils @Inject constructor(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}