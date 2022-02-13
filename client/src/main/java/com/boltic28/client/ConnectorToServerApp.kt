package com.boltic28.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.IBinder
import com.boltic28.aidlinterface.ServerAssistant

class ConnectorToServerApp(
    private val packageManager: PackageManager,
) {

    private var mServerAssistant: ServerAssistant? = null
    val serverAssistant: ServerAssistant?
        get() = mServerAssistant

    val serverConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mServerAssistant = ServerAssistant.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mServerAssistant = null
        }
    }

    val connectionIntent: Intent
        get() = run {
            val intent = Intent("com.boltic28.server.REMOTE_CONNECTION")
            val services = packageManager.queryIntentServices(intent, 0)
            if (services.isEmpty()) {
                throw IllegalStateException("there is no connection to the server app")
            }
            return Intent(intent).apply {
                val resolveInfo = services[0]
                val packageName = resolveInfo.serviceInfo.packageName
                val className = resolveInfo.serviceInfo.name
                component = ComponentName(packageName, className)
            }
        }
}