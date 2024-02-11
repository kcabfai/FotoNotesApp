package cz.utb.fai.fotonotesapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.utb.fai.fotonotesapp.R
import java.text.SimpleDateFormat
import java.util.Date

class NoteAdapter(private val mNotes : ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<TextView>(R.id.textViewTitle)
        val contentView = itemView.findViewById<TextView>(R.id.textViewContent)
        val stampView = itemView.findViewById<TextView>(R.id.textTimeStamp)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.note_item, parent, false)

        return ViewHolder(noteView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note: Note = mNotes.get(position)

        val titleView = holder.titleView
        val contentView = holder.contentView
        val stampView = holder.stampView

        titleView.setText(note.title)
        contentView.setText(note.content)
        stampView.setText(SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(Date(note.timestamp)))

    }

    override fun getItemCount(): Int {
        return mNotes.size
    }
}