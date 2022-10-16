package com.devsaul.bookfinderapp.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgument
import androidx.recyclerview.widget.RecyclerView
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.ui.home.fragments.SearchFragment
import com.devsaul.bookfinderapp.utils.setBookInfo
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class BooksAdapter(val books: List<Book>, val fragment: SearchFragment) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var subTitle: TextView
        var date: TextView
        var numberOfPages: TextView
        var cardItem: MaterialCardView
        var img: ImageView

        init {
            title = itemView.findViewById(R.id.tvTitle)
            subTitle = itemView.findViewById(R.id.tvSubtitleDesp)
            date = itemView.findViewById(R.id.tvDate)
            cardItem = itemView.findViewById(R.id.cardBook)
            img = itemView.findViewById(R.id.imgBook)
            numberOfPages = itemView.findViewById(R.id.tvPagNumber)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.book_list_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val book = books[i]
        val imgUrl = "https://covers.openlibrary.org/b/isbn/${book.isbn?.get(0)}-M.jpg"
        val authorText = "Autor: " + (book.author_name?.joinToString(", ") ?: "a")
        val dateText = "Fecha publicación: " + book.first_publish_year.toString()
        val pageText = "Numero de paginas: " + book.number_of_pages_median.toString()

        viewHolder.title.text = book.title
        viewHolder.subTitle.setBookInfo(authorText, viewHolder.subTitle)
        viewHolder.date.setBookInfo(dateText, viewHolder.date)
        viewHolder.numberOfPages.setBookInfo(pageText, viewHolder.numberOfPages)

        Picasso.get()
            .load(imgUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_image_not_supported)
            .into(viewHolder.img)

        viewHolder.cardItem.setOnClickListener {
            fragment.setAlert(book)
        }

    }

    override fun getItemCount(): Int {
        return books.size
    }

}

