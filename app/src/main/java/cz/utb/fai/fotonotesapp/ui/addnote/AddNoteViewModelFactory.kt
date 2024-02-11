package cz.utb.fai.fotonotesapp.ui.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.fotonotesapp.repository.NoteRepository

class AddNoteViewModelFactory(
    private val repository : NoteRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            return AddNoteViewModel(repository) as T
        }

        throw IllegalArgumentException("Wrong ViewModel class")
    }
}