package com.example.broadcast_client

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.broadcast_client.ui.theme.AIDLClientTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIDLClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val mHandler = Handler()
        val monitor: Runnable = Runnable { connectToRemoteService() }
        mHandler.postDelayed(monitor, 3000)
    }

    private fun connectToRemoteService() {

        val intent = Intent()
        intent.action = "com.example.broadcast.server"
        intent.putExtra("KEY", "This is a broad cast Message")
        intent.component = ComponentName("com.example.broadcast.server", "com.example.broadcast_server.IPCBroadcastReceiver")
        applicationContext?.sendBroadcast(intent)
    }
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {

    Text(
        text = "Hello !",
        modifier = modifier
    )
}
