package de.abyshkin.notesappmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import de.abyshkin.notesappmvvm.model.Note

class AllNotesLiveData: LiveData<List<Note>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            //Создаем список заметок
            val notes = mutableListOf<Note>()
            //Заполнение созданого списка notes
            snapshot.children.map {
                notes.add(it.getValue(Note::class.java) ?: Note())
            }
            //Присваеваем слушателю список заметок
            value = notes
        }

        override fun onCancelled(error: DatabaseError) { }

    }

    override fun onActive() {
        //Обращаемся к базе и передаем слушатель
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        //Обращаемся к базе и удаляем слушатель
        database.removeEventListener(listener)
        super.onInactive()
    }
}