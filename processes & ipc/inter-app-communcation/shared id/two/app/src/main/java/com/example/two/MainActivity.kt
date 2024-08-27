package com.example.two

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.two.ui.theme.TwoTheme
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val sourceApp = "com.example.one"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", context = applicationContext)
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,  context: Context) {
    Column {


        Button(onClick = {}) {

            val applicationInfo = context.packageManager.getPackageInfo(sourceApp, PackageManager.PackageInfoFlags.of(0))
            val path = applicationInfo.applicationInfo.dataDir + "/files/savefiles/readme"

//            val directory = File(context.filesDir, "savefiles")
//            if (!directory.exists()) {
//                directory.mkdirs()
//            }
//
//            val file = File(directory, "readme")

            try {
                var fileInputStream: FileInputStream? = null
                fileInputStream = FileInputStream(path)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    // stringBuilder.append(text)
                    Log.e("MainActivity", text!!)
                }
            } catch (e: IOException) {
                Log.e("Exception", "File write failed: $e")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TwoTheme {
    }
}