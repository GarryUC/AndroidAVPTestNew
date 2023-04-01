package com.it.task.utility

import android.content.Context
import android.net.ConnectivityManager

class Utility(private val context: Context) {

    val isNetworkAvailable: Boolean
        get() {
            try {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = cm.activeNetworkInfo
                if (networkInfo != null && networkInfo.isConnected)
                    return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }
}