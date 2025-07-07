package com.ulbra.storebooks.data.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar

import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.DetailsActivity
import com.ulbra.storebooks.R
import com.ulbra.storebooks.data.ui.adapter.StoreBookAdapter
import com.ulbra.storebooks.data.ui.viewModels.BookViewModel
import com.ulbra.storebooks.data.ui.viewModels.BookViewModelFactory
import com.ulbra.storebooks.databinding.FragmentListBookBinding

class ListBookFragment : Fragment() {

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentListBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: StoreBookAdapter
    private lateinit var detailsLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_book, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getParcelableExtra<StoreBook>("updatedBook")?.let { updated ->
                    bookViewModel.updateBook(updated)
                    Snackbar.make(binding.recyclerBooks, "Livro atualizado com sucesso!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        adapter = StoreBookAdapter(
            onDelete = { book ->
                bookViewModel.deleteBook(book)
                Snackbar.make(binding.recyclerBooks, "Livro excluído com sucesso!", Snackbar.LENGTH_SHORT).show()
            },
            onDetails = { book -> openDetails(book) },
            onSelect = { book -> selectBook(book) },
            onShowSelectMessage = { /* não mostrar nada */ },
            onShowDeleteMessage = { /* não mostrar nada */ },
            onEdit = { book -> openEditForm(book) }
        )

        binding.recyclerBooks.adapter = adapter

        // Observa a lista e atualiza UI
        bookViewModel.filteredBooks.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            binding.textEmptyState.visibility = if (books.isEmpty()) View.VISIBLE else View.GONE
        }

        // Busca por nome (filtro)
        binding.inputSearch.addTextChangedListener { editable ->
            val query = editable.toString()
            bookViewModel.filterBooks(query)
        }

        // Adicionar novo livro
        binding.fabAddBook.setOnClickListener {
            openFormBook()
        }

        // Receber livros criados/atualizados por outros fragments
        parentFragmentManager.setFragmentResultListener("bookCreated", viewLifecycleOwner) { _, bundle ->
            bundle.getParcelable<StoreBook>("newBook")?.let {
                bookViewModel.addBook(it)
                Snackbar.make(binding.recyclerBooks, "Livro adicionado com sucesso", Snackbar.LENGTH_SHORT).show()
            }
            bundle.getParcelable<StoreBook>("updatedBook")?.let {
                bookViewModel.updateBook(it)
                Snackbar.make(binding.recyclerBooks, "Livro atualizado com sucesso", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun openFormBook() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, FormBookFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openDetails(book: StoreBook) {
        val intent = Intent(requireContext(), DetailsActivity::class.java).apply {
            putExtra("book", book)
        }
        detailsLauncher.launch(intent)
    }

    private fun selectBook(book: StoreBook) {
        val updatedList = bookViewModel.filteredBooks.value.orEmpty().map {
            it.copy(isSelected = it.id == book.id)
        }
        adapter.submitList(updatedList)
    }

    private fun openEditForm(book: StoreBook) {
        val bundle = Bundle().apply {
            putParcelable("editBook", book)
        }
        val formFragment = FormBookFragment()
        formFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.main, formFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}