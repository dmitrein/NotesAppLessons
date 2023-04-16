@file:OptIn(ExperimentalMaterial3Api::class)

package de.abyshkin.notesappmvvm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.abyshkin.notesappmvvm.navigation.NotesNavHost
import de.abyshkin.notesappmvvm.ui.theme.NotesAppMVVMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppMVVMTheme {
                DefaultScreen()
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NotesAppMVVMTheme {
//
//    }
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DefaultScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Notes App")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
                //contentColor = Color.Black
            ) {
                NotesNavHost()
            }
        }
    )
}