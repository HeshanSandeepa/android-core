
package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log

class CalculatorService : Service() {

    private val mMessenger = Messenger(IncomingHandler())


    override fun onBind(intent: Intent?): IBinder {
        //return mBinder
        return mMessenger.binder
    }

    // Implement the AIDL interface
    private val mBinder: ICalculatorService.Stub = object : ICalculatorService.Stub() {
        @Throws(RemoteException::class)
        override fun add(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    }

    internal inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // Get message from client. Save recent connected client info.
            val receivedBundle = msg.data
            Log.e("Server || From Client ", receivedBundle.getInt("F_C_S").toString())

            // Send message to the client. The message contains server info
            val message = Message.obtain(this@IncomingHandler, 0)
            val bundle = Bundle()
            bundle.putInt("F_S_C", 249)
            message.data = bundle
            // The service can save the msg.replyTo object as a local variable
            // so that it can send a message to the client at any time
            msg.replyTo.send(message)
        }
    }
}