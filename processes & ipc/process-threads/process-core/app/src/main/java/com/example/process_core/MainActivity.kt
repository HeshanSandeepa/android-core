package com.example.process_core

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.process_core.ui.theme.ProcesscoreTheme

enum class Screens() {
    Main,
    Second
}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProcesscoreTheme {
                GreetingApp({

                    /* navController.navigate(Screens.Second.name)*/
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun GreetingApp(onClick: () -> Unit = {},  navController: NavHostController = rememberNavController()) {
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = Screens.Main.name,
            modifier = Modifier.padding(it)
        ) {

            composable(route = Screens.Main.name) {
                Greeting(onClick = onClick)
            }

            composable(route = Screens.Second.name) {
                SecondActivity()
            }
        }
    }
}

@Composable
fun Greeting(   onClick: () -> Unit = {}, modifier: Modifier = Modifier) {
    Text(
        text = "Hello First!",
        modifier = modifier
    )
    Button(onClick = onClick) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProcesscoreTheme {
        Greeting()
    }
}