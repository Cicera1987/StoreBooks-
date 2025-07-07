package com.ulbra.storebooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulbra.storebooks.data.StoreBook
import com.ulbra.storebooks.data.ui.fragments.DetailsBookFragment

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val book = intent.getParcelableExtra<StoreBook>("book")

        if (savedInstanceState == null && book != null) {
            val fragment = DetailsBookFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("book", book)
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
