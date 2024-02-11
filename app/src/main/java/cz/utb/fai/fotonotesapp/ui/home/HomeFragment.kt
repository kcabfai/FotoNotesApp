package cz.utb.fai.fotonotesapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.utb.fai.fotonotesapp.MyApplication
import cz.utb.fai.fotonotesapp.databinding.FragmentHomeBinding
import cz.utb.fai.fotonotesapp.model.Note
import cz.utb.fai.fotonotesapp.model.NoteAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var notes: ArrayList<Note>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val app = activity?.application as MyApplication
        val homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(app.notesRepository)).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val list = ArrayList<Note>()
        list.add(Note("Hello There", "How is this?", System.currentTimeMillis()))
        list.add(Note("Hello There Twice", "How is this?", System.currentTimeMillis()))*/

        Log.d("ECHO", "EHOJ EHOJ")

        if(homeViewModel.notes.value != null) {
            notes = homeViewModel.notes.value !!
            binding.rvNotes.adapter = NoteAdapter(notes)
            binding.rvNotes.layoutManager = LinearLayoutManager(this.context)
        }

        Log.d("ECHO", "EHOJ EHOJ2")





        Log.d("ECHO", "EHOJ EHOJ3")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}