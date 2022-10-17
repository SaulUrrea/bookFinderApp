package com.devsaul.bookfinderapp.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.MaterialAlertBookBinding
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.ui.home.fragments.FavFragment
import com.devsaul.bookfinderapp.ui.home.viewmodel.FavViewModel
import com.devsaul.bookfinderapp.utils.setBookInfo
import com.devsaul.bookfinderapp.utils.setUpViewAler
import com.devsaul.bookfinderapp.utils.toast
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class BooksAdapter(var books: MutableList<Book>, val fragment: Fragment, val favViewModel: FavViewModel) :
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

    override fun onBindViewHolder(viewHolder: ViewHolder, posicion: Int) {
        val book = books[posicion]
        val imgUrl = "https://covers.openlibrary.org/b/isbn/${book.isbn?.get(0)}-M.jpg"
        val authorText = "Autor: " + (book.author_name?.joinToString(", ") ?: "a")
        val dateText = "Fecha publicación: " + book.first_publish_year.toString()
        val pageText = "Numero de paginas: " + book.number_of_pages_median.toString()

        Log.i("image",imgUrl)

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
            setAlert(book)
        }

    }

    private fun setAlert(book: Book) {
        val bindingAlert: MaterialAlertBookBinding =
            MaterialAlertBookBinding.inflate(fragment.layoutInflater)
        val bookDialogAlert = MaterialAlertDialogBuilder(
            fragment.requireContext(),
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setView(bindingAlert.root)
            .setNegativeButton(fragment.requireContext().getString(R.string.close)) { dialog, which ->
                dialog.dismiss()
            }
            .create()
        bookDialogAlert.show()
        bindingAlert.fbAddFav.setOnClickListener {
            favViewModel.addFavorite(book)
            fragment.toast(fragment.requireContext().getString(R.string.fav_add_message))
        }
        bindingAlert.fbDeleteFav.setOnClickListener {
            favViewModel.removeFavorite(book)
            favViewModel.bookIsRemove.observe(fragment.viewLifecycleOwner){
                if (it){
                    books.remove(book)
                    favViewModel.books.postValue(books)
                    notifyDataSetChanged()
                }
            }
            fragment.toast(fragment.requireContext().getString(R.string.fav_remove_message))
            bookDialogAlert.dismiss()
        }
        bookDialogAlert.setUpViewAler(book, bindingAlert,(fragment is FavFragment))
    }

    override fun getItemCount(): Int {
        return books.size
    }

}

