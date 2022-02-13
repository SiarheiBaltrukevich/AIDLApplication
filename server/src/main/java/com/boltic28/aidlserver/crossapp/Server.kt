package com.boltic28.aidlserver.crossapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.boltic28.aidlinterface.ServerAssistant

class Server : Service() {

    override fun onBind(p0: Intent?): IBinder = object : ServerAssistant.Stub() {

        override fun numberOfCars(): Int = 146

        override fun numberOfBuses(): Int = 57

        override fun numberOfBikes(): Int = 24

        override fun numberOfBoats(): Int = 17

    }

}