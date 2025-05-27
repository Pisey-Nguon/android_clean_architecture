package com.pisey.cleanarchitecture.utils

import android.content.Context
import android.net.ConnectivityManager

object ConnectUtils {
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}