package cz.utb.fai.fotonotesapp.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.utb.fai.fotonotesapp.model.Note

class AddNoteViewModel : ViewModel() {
    private var _note = MutableLiveData<Note>()

    var note: LiveData<Note> = _note

    fun createAndSaveNote(title: String, content: String) {
        val newNote = Note(title, content, System.currentTimeMillis())
        saveNote(newNote)
        _note.value = newNote

    }

    fun saveNote(note: Note) {
        //saving data
    }

    fun clearNote() {
        _note.value = Note("", "", System.currentTimeMillis())
    }

}