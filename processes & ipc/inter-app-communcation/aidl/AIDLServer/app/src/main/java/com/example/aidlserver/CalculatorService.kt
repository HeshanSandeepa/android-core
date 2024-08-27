
package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException

class CalculatorService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    // Implement the AIDL interface
    private val mBinder: ICalculatorService.Stub = object : ICalculatorService.Stub() {
        @Throws(RemoteException::class)
        override fun add(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    }
}