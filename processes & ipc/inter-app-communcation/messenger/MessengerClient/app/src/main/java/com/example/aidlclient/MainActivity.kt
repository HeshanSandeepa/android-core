package com.example.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aidlclient.ui.theme.AIDLClientTheme
import com.example.aidlserver.ICalculatorService


class MainActivity : ComponentActivity(), ServiceConnection {

    private var calculatorService: ICalculatorService? = null

    private var serverMessenger: Messenger? = null

    // Messenger on the client
    private var clientMessenger: Messenger? = null

    // Handle messages from the remote service
    private var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // Update UI with remote process info
            val bundle = msg.data
            Log.e("Client || From Server ", bundle.getInt("F_S_C").toString())
        }
    }

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
        mHandler.postDelayed(monitor, 5000)
    }

    private fun connectToRemoteService() {
        val intent = Intent("messengerexample")
        intent.setPackage("com.example.aidlserver");
        applicationContext?.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    private fun disconnectToRemoteService() {
        calculatorService = null;
    }

    override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
        Log.e("onServiceConnected: pid: ", "hello")

        serverMessenger = Messenger(service)

        val message = Message.obtain(handler)
        val bundle = Bundle()
        bundle.putInt("F_C_S", 124)
        message.data = bundle
        message.replyTo = clientMessenger // we offer our Messenger object for communication to be two-way
        try {
            serverMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        } finally {
            message.recycle()
        }
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        Toast.makeText(applicationContext, "IPC server has disconnected unexpectedly", Toast.LENGTH_LONG).show()
        calculatorService = null
    }
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {

    Text(
        text = "Hello !",
        modifier = modifier
    )
}
