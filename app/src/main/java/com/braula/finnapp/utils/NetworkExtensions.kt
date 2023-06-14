package com.braula.finnapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.isNetworkAvailable(): Boolean {
    return getConnectionType() != ConnectionType.NO_CONNECTION
}

private fun Context.getConnectionType(): ConnectionType {
    var connectionType = ConnectionType.NO_CONNECTION
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
        if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            connectionType = ConnectionType.WIFI
        else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            connectionType = ConnectionType.CELLULAR
    }
    return connectionType
}

private enum class ConnectionType {
    NO_CONNECTION, WIFI, CELLULAR
}