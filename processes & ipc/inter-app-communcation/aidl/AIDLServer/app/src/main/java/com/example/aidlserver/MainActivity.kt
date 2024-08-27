package com.example.aidlserver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aidlserver.data.RecentClient
import com.example.aidlserver.ui.theme.AIDLServerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIDLServerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val client = RecentClient.client

        if (client == null) {
            Log.e("aidlserver ", "Client Null")
        } else {
            client.clientPackageName?.let { Log.e("aidlserver: packageName: ", it) }
            client.clientProcessId.let { Log.e("aidlserver: clientProcessId: ", it) }
            client.clientData?.let { Log.e("aidlserver: clientData: ", it) }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val client = RecentClient.client
    val connectionStatus = if (client == null) " Not Connected" else "Connected"

    Column {
        Text(
            text = "Status: $connectionStatus",
            modifier = modifier
        )
        Text(
            text = "Package Name: ${client?.clientPackageName}",
            modifier = modifier
        )
        Text(
            text = "Process Id: ${client?.clientProcessId}",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AIDLServerTheme {
        Greeting("Android")
    }
}