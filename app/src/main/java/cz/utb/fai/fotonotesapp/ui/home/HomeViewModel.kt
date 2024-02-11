package cz.utb.fai.fotonotesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.utb.fai.fotonotesapp.model.Note

class HomeViewModel : ViewModel() {
    private val _notes = MutableLiveData<ArrayList<Note>>().apply {
        val list = ArrayList<Note>()
        list.add(Note("Hello There", "How is this?", System.currentTimeMillis()))
        list.add(Note("Hello There Twice", "How is this?", System.currentTimeMillis()))
        value = list
    }
    val notes: LiveData<ArrayList<Note>> = _notes


    /*private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/
}