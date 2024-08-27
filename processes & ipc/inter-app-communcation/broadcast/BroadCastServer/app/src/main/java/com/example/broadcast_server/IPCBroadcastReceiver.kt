package com.example.broadcast_server

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class IPCBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        intent?.getStringExtra("KEY")?.let { Log.e("IPCBroadcastReceiver", it) }
    }
}