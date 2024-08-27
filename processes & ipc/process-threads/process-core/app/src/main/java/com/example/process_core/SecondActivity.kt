package com.example.process_core

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.process_core.ui.theme.ProcesscoreTheme


class SecondActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProcesscoreTheme {
                Second()
            }
        }
    }
}



@Composable
fun Second(modifier: Modifier = Modifier) {
    Text(
        text = "Second ",
        modifier = modifier
    )
}
