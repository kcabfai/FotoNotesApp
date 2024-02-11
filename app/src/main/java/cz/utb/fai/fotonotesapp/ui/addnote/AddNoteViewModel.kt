package cz.utb.fai.fotonotesapp.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.utb.fai.fotonotesapp.model.Note
import cz.utb.fai.fotonotesapp.repository.NoteRepository

class AddNoteViewModel(private val repository: NoteRepository) : ViewModel() {
    private var _note = MutableLiveData<Note>()

    var note: LiveData<Note> = _note

    suspend fun createAndSaveNote(title: String, content: String) {
        val newNote = Note(title, content, System.currentTimeMillis())
        saveNote(newNote)
        _note.value = newNote

    }

    suspend fun saveNote(note: Note) {
        repository.addNote(note)
        clearNote()
    }

    fun clearNote() {
        _note.value = Note("", "", System.currentTimeMillis())
    }

}