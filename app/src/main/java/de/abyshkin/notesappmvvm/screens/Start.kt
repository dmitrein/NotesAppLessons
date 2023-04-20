package de.abyshkin.notesappmvvm.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.abyshkin.notesappmvvm.ui.theme.NotesAppMVVMTheme

@Composable
fun StartScreen(navController: NavHostController) {
    Text(
        text = "Notes App Screen"
    )
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen(){
    NotesAppMVVMTheme {
        StartScreen(navController = rememberNavController())
    }
}