@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package de.abyshkin.notesappmvvm

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import de.abyshkin.notesappmvvm.navigation.NavRoute
import de.abyshkin.notesappmvvm.navigation.NotesNavHost
import de.abyshkin.notesappmvvm.ui.theme.NotesAppMVVMTheme
import de.abyshkin.notesappmvvm.utils.DB_TYPE

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppMVVMTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "Notes App")
                                    if (DB_TYPE.value.isNotEmpty()){
                                        Icon(
                                            imageVector = Icons.Default.ExitToApp,
                                            contentDescription = "",
                                            modifier = Modifier.clickable {
                                                mViewModel.singOut {
                                                    navController.navigate(NavRoute.Start.route) {
                                                        popUpTo(NavRoute.Start.route) {
                                                            inclusive = true
                                                        }
                                                    }
                                                }
                                            }
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    },
                    content = {
                        Surface(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                            //contentColor = Color.Black
                        ) {
                            NotesNavHost(mViewModel, navController)
                        }
                    }
                )
            }
        }
    }
}