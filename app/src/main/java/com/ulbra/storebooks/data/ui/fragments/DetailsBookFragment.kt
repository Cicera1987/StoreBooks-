package com.ulbra.storebooks.data.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.ulbra.storebooks.R

import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.databinding.FragmentDetailsBookBinding

import com.ulbra.storebooks.data.ReadingStatus as ReadingStatus1

class DetailsBookFragment : Fragment() {

    private var _binding: FragmentDetailsBookBinding? = null
    private val binding get() = _binding!!

    private var book: StoreBook? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_book, container, false)
        return binding.root
    }

    private fun updateFavoriteUI(isFavorite: Boolean) {
        binding.detailFavoriteToggle.setImageResource(
            if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outline
        )
        val colorRes = if (isFavorite) R.color.yellow else R.color.gray
        binding.detailFavoriteToggle.imageTintList = ContextCompat.getColorStateList(requireContext(), colorRes)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        book = arguments?.getParcelable("book")

        book?.let { b ->

            binding.storebook = b

            val statusOptions = ReadingStatus1.entries.map { it.name.replace("_", " ") }
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statusOptions)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.detailStatusSpinner.adapter = adapter
            binding.detailStatusSpinner.setSelection(b.status.ordinal)

            updateFavoriteUI(b.favorite)

            binding.detailStatusSpinner.setOnItemSelectedListener(object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (book?.status?.ordinal != position) {
                        book = book?.copy(status = ReadingStatus1.entries[position])
                        book?.let { sendUpdatedBook(it) }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })

            binding.detailFavoriteToggle.setOnClickListener {
                book = book?.let { it.copy(favorite = !it.favorite) }
                book?.let {
                    updateFavoriteUI(it.favorite)
                    sendUpdatedBook(it)
                }
            }
        }
    }

    private fun sendUpdatedBook(book: StoreBook) {
        parentFragmentManager.setFragmentResult(
            "bookCreated", Bundle().apply { putParcelable("updatedBook", book) }
        )

        requireActivity().setResult(
            Activity.RESULT_OK,
            Intent().putExtra("updatedBook", book)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
