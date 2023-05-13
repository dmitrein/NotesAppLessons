package de.abyshkin.notesappmvvm.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.abyshkin.notesappmvvm.MainViewModel
import de.abyshkin.notesappmvvm.screens.AddScreen
import de.abyshkin.notesappmvvm.screens.MainScreen
import de.abyshkin.notesappmvvm.screens.NoteScreen
import de.abyshkin.notesappmvvm.screens.StartScreen
import de.abyshkin.notesappmvvm.utils.Constants

sealed class NavRoute(val route: String) {
    object Start : NavRoute(Constants.Screens.START_SCREEN)
    object Main : NavRoute(Constants.Screens.MAIN_SCREEN)
    object Add : NavRoute(Constants.Screens.ADD_SCREEN)
    object Note : NavRoute(Constants.Screens.NOTE_SCREEN)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{${Constants.Keys.ID}}") { backStackEntry ->
            NoteScreen(
                navController = navController,
                viewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(Constants.Keys.ID)
            )
        }
    }
}