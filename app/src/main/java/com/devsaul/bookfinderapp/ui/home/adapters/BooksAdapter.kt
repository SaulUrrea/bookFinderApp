package com.devsaul.bookfinderapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.domain.models.Book
import com.google.android.material.card.MaterialCardView

class BooksAdapter(val books: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var subTitle: TextView
        var date: TextView
        var btnFav: ToggleButton
        var cardItem: MaterialCardView

        init {
            title = itemView.findViewById(R.id.tvTitle)
            subTitle = itemView.findViewById(R.id.tvSubtitleDesp)
            date = itemView.findViewById(R.id.tvDate)
            btnFav = itemView.findViewById(R.id.cbFav)
            cardItem = itemView.findViewById(R.id.cardBook)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.book_list_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val book = books[i]
        viewHolder.title.text = book.title
        viewHolder.subTitle.text = "Autor: " + (book.author_name?.get(0) ?: "N/A")
        viewHolder.date.text = "Publicado en: " + book.first_publish_year.toString()
        viewHolder.cardItem.setOnClickListener {

        }
        viewHolder.btnFav.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return books.size
    }

}

