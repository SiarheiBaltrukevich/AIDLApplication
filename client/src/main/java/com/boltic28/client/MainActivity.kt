package com.boltic28.client

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boltic28.client.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var connector: ConnectorToServerApp
    private lateinit var binding: ActivityMainBinding

    private val serverApp
        get() = connector.serverAssistant

    private val clicker = object : Clicker {
        override fun onCom1Click() =
            handleResult("\n com1 -> there are ${serverApp?.numberOfCars()} cars on the parking")

        override fun onCom2Click() =
            handleResult("\n com2 -> there are ${serverApp?.numberOfBikes()} bikes on the parking")

        override fun onCom3Click() =
            handleResult("\n com3 -> there are ${serverApp?.numberOfBuses()} buses on the parking")

        override fun onCom4Click() =
            handleResult("\n com4 -> there are ${serverApp?.numberOfBoats()} boats on the parking")

    }

    private fun handleResult(result: String) {
        val text = binding.logs.text.toString() + result
        binding.logs.text = text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connector = ConnectorToServerApp(packageManager)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.clicker = clicker

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        bindService(connector.connectionIntent, connector.serverConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop()  {
        super.onStop()
        unbindService(connector.serverConnection)
    }
}