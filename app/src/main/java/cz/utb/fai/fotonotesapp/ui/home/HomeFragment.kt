package cz.utb.fai.fotonotesapp.ui.home

import android.os.Bundle
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

        notes = ArrayList<Note>()
        homeViewModel.notes.value?.let {
            notes.addAll(it)
        }

        binding.rvNotes.adapter = NoteAdapter(notes)
        binding.rvNotes.layoutManager = LinearLayoutManager(this.context)

        homeViewModel.notes.observe(viewLifecycleOwner) { newNotes ->
            notes.clear()
            notes.addAll(newNotes)
            binding.rvNotes.adapter?.notifyDataSetChanged()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}