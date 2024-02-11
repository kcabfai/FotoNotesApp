package cz.utb.fai.fotonotesapp

import android.app.Application
import cz.utb.fai.fotonotesapp.repository.NoteRepository

class MyApplication : Application() {
    val notesRepository : NoteRepository by lazy {
        NoteRepository(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}