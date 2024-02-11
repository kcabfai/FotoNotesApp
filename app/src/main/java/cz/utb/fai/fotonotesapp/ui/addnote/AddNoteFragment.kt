package cz.utb.fai.fotonotesapp.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import cz.utb.fai.fotonotesapp.MyApplication
import cz.utb.fai.fotonotesapp.databinding.FragmentAddnoteBinding
import cz.utb.fai.fotonotesapp.ui.home.HomeViewModelFactory
import kotlinx.coroutines.launch

class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddnoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val app = activity?.application as MyApplication
        val addNoteViewModel =
            ViewModelProvider(this, AddNoteViewModelFactory(app.notesRepository)).get(AddNoteViewModel::class.java)

        _binding = FragmentAddnoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val clearButton: Button = binding.clearButton
        val saveButton : Button = binding.saveButton

        clearButton.setOnClickListener {
            addNoteViewModel.clearNote()
        }

        saveButton.setOnClickListener {
            lifecycleScope.launch() {
                addNoteViewModel.createAndSaveNote(
                    binding.editNoteTitle.text.toString(),
                    binding.editTextTextMultiLine.text.toString()
                )
            }
        }

        addNoteViewModel.note.observe(viewLifecycleOwner) {
            binding.editNoteTitle.setText(it.title)
            binding.editTextTextMultiLine.setText(it.content)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}