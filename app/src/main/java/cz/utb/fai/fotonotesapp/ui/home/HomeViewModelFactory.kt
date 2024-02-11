package cz.utb.fai.fotonotesapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.fotonotesapp.repository.NoteRepository

class HomeViewModelFactory(
    private val repository : NoteRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Wrong ViewModel class")
    }
}