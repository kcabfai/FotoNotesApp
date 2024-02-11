package cz.utb.fai.fotonotesapp.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import cz.utb.fai.fotonotesapp.model.Note
import java.io.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class NoteRepository (context: Context) {
    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "Notes")
    private val dataStore = context.dataStore

    private val gson = Gson()

    companion object {
        val notes = stringPreferencesKey("LIST_OF_NOTES_KEY")
    }
    suspend fun setNotes(data : ArrayList<Note>) {
        dataStore.edit {pref ->
            pref[notes] = gson.toJson(data)
        }
    }

    suspend fun addNote(note: Note) {
        val existingNotes = getNotes().first()

        existingNotes.add(note)

        setNotes(existingNotes)
    }


    fun getNotes() : Flow<ArrayList<Note>> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {pref ->
                val storedData = pref[notes] ?: "[]"
                val typeToken = object : TypeToken<ArrayList<Note>>() {}

                try {
                    val storedNotes = gson.fromJson(storedData, typeToken)
                    Log.d("JSONERROR", "This OK")
                    storedNotes

                } catch (ex: JsonSyntaxException) {
                    Log.d("JSONERROR", "Json nebyl pochopen")
                    ArrayList<Note>()
                }
            }
    }
}