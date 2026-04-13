package com.nic.mparivahan.core.util


import android.Manifest
import android.content.Context
import android.net.*
import androidx.annotation.RequiresPermission
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val cap = cm.getNetworkCapabilities(network) ?: return false
        return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
