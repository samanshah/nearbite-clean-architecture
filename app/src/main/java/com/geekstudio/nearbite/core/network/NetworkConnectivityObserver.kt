package com.geekstudio.nearbite.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkConnectivityObserver
@Inject constructor(
    @ApplicationContext context: Context
) : ConnectivityObserver {

    private val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    override fun observe() = callbackFlow {

        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(
                network: Network
            ) {

                trySend(
                    ConnectivityObserver.Status.Available
                )

            }

            override fun onLost(
                network: Network
            ) {

                trySend(
                    ConnectivityObserver.Status.Lost
                )

            }

        }

        connectivityManager.registerDefaultNetworkCallback(
                callback
            )

        awaitClose {

            connectivityManager.unregisterNetworkCallback(
                    callback
                )

        }

    }

}