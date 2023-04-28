package de.abyshkin.notesappmvvm.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.abyshkin.notesappmvvm.model.Note

@Dao
interface NoteRoomDao {

    @Query("select * from notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}