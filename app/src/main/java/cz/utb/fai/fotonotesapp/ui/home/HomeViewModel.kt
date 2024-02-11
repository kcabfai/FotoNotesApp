package cz.utb.fai.fotonotesapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cz.utb.fai.fotonotesapp.model.Note
import cz.utb.fai.fotonotesapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _notes = MutableLiveData<ArrayList<Note>>()

    val notes: LiveData<ArrayList<Note>> = _notes

    init {
        viewModelScope.launch {
            repository.getNotes().collect() {newNote ->
                _notes.value = newNote
            }
        }
    }

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/
}