package com.ulbra.storebooks.data.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ulbra.storebooks.data.ReadingStatus
import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.databinding.FragmentFormBookBinding


class FormBookFragment : Fragment() {

    private var _binding: FragmentFormBookBinding? = null
    private val binding get() = _binding!!

    // Livro que estamos editando, pode ser nulo se for criação
    private var editingBook: StoreBook? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editingBook = arguments?.getParcelable("editBook")

        editingBook?.let {
            binding.inputTitle.setText(it.title)
            binding.inputAuthor.setText(it.author)
            binding.inputYear.setText(it.yearPublication.toString())
            binding.inputDescription.setText(it.description)
            binding.inputImageUrl.setText(it.imageUrl)
        }

        binding.btnSave.setOnClickListener {
            saveBook()
        }
    }

    private fun saveBook() {
        val title = binding.inputTitle.text?.toString()?.trim()
        val author = binding.inputAuthor.text?.toString()?.trim()
        val yearText = binding.inputYear.text?.toString()?.trim()
        val description = binding.inputDescription.text?.toString()?.trim()
        val imageUrl = binding.inputImageUrl.text?.toString()?.trim() ?: ""

        if (title.isNullOrEmpty() || author.isNullOrEmpty() || yearText.isNullOrEmpty() || description.isNullOrEmpty()) {
            Snackbar.make(binding.root, "Preencha todos os campos obrigatórios!", Snackbar.LENGTH_SHORT).show()
            return
        }

        val year = yearText.toIntOrNull()
        if (year == null) {
            Snackbar.make(binding.root, "Ano inválido!", Snackbar.LENGTH_SHORT).show()
            return
        }

        val updatedBook = StoreBook(
            id = editingBook?.id ?: (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
            title = title,
            author = author,
            yearPublication = year,
            description = description,
            imageUrl = imageUrl,
            status = editingBook?.status ?: ReadingStatus.NAO_LIDO,
            favorite = editingBook?.favorite ?: false
        )

        val result = Bundle().apply {
            val key = if (editingBook == null) "newBook" else "updatedBook"
            putParcelable(key, updatedBook)
        }
        parentFragmentManager.setFragmentResult("bookCreated", result)
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
