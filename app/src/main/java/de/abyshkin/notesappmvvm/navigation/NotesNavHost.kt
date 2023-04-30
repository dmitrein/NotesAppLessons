package de.abyshkin.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.abyshkin.notesappmvvm.MainViewModel
import de.abyshkin.notesappmvvm.screens.AddScreen
import de.abyshkin.notesappmvvm.screens.MainScreen
import de.abyshkin.notesappmvvm.screens.NoteScreen
import de.abyshkin.notesappmvvm.screens.StartScreen

sealed class NavRoute(val route: String){
    object Start:NavRoute("start_screen")
    object Main:NavRoute("main_screen")
    object Add:NavRoute("add_screen")
    object Note:NavRoute("note_screen")
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route){
        composable(NavRoute.Start.route){ StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route){ MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route){ AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route){ NoteScreen(navController = navController, viewModel = mViewModel) }
    }
}