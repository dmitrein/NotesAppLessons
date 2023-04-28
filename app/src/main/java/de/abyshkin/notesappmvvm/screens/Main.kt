@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package de.abyshkin.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.abyshkin.notesappmvvm.MainViewModel
import de.abyshkin.notesappmvvm.MainViewModelFactory
import de.abyshkin.notesappmvvm.model.Note
import de.abyshkin.notesappmvvm.navigation.NavRoute
import de.abyshkin.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    ) {
        /*LazyColumn {
            items(notes) {note ->
                NoteItem(note = note, navController = navController)
            }
        }*/
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 18.dp)
            .clickable { navController.navigate(NavRoute.Note.route) },
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = note.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = note.subtitle
        )
    }
}
@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    NotesAppMVVMTheme {
        MainScreen(navController = rememberNavController())
    }
}