package com.ulbra.storebooks

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ulbra.storebooks.data.ReadingStatus
import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.data.ui.fragments.ListBookFragment

class MainActivity : AppCompatActivity() {

    private val bookList = listOf(
        StoreBook(1, "O Hobbit", "J.R.R. Tolkien", 1937, "Uma aventura épica na Terra Média.", "", ReadingStatus.LIDO, false),
        StoreBook(2, "1984", "George Orwell", 1949, "Um mundo distópico e totalitário.", "", ReadingStatus.LENDO, true),
        StoreBook(3, "A Revolução dos Bichos", "George Orwell", 1945, "Uma fábula política sobre animais.", "", ReadingStatus.NAO_LIDO, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragment = ListBookFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("bookList", ArrayList(bookList))
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()

    }
}