@file:OptIn(ExperimentalMaterial3Api::class)

package de.abyshkin.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import de.abyshkin.notesappmvvm.utils.Constants
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull {
        it.id == noteId?.toInt()
    } ?: Note(
        title = Constants.Keys.NONE,
        subtitle = Constants.Keys.NONE
    )

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetScaffoldStateState = rememberBottomSheetScaffoldState(SheetState(openBottomSheet))
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldStateState,
        sheetPeekHeight = 320.dp,
        sheetContent = {
            Surface {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = Constants.Keys.TITLE) },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text(text = Constants.Keys.SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note = Note(
                                id = note.id,
                                title = note.title,
                                subtitle = note.subtitle
                            )){
                                navController.navigate(NavRoute.Main.route)
                            }
                        }) {
                            Text(text = Constants.Keys.UPDATE_NOTE)
                    }
                }
            }
        }) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = note.title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                            Text(
                                text = note.subtitle,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(top = 14.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(onClick = {
                            coroutineScope.launch {
                                title = note.title
                                subtitle = note.subtitle
                                //openBottomSheet = !openBottomSheet
                                //bottomSheetState.show()
                                bottomSheetScaffoldStateState.bottomSheetState.show()
                            }
                        }) {
                            Text(text = Constants.Keys.UPDATE)
                        }
                        Button(onClick = {
                            viewModel.deleteNote(note = note){
                                navController.navigate(NavRoute.Main.route)
                            }
                        }
                        ) {
                            Text(text = Constants.Keys.DELETE)
                        }
                    }
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth(),
                        onClick = {
                            navController.navigate(NavRoute.Main.route)
                        }) {
                        Text(text = Constants.Keys.NAV_BACK)
                    }
                }
            }
    }

}


@Preview(showBackground = true)
@Composable
fun prevNoteScreen() {
    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1,"
        )
    }
}