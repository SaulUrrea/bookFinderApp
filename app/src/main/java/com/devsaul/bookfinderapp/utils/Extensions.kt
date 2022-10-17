package com.devsaul.bookfinderapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.IBinder
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.MaterialAlertBookBinding
import com.devsaul.bookfinderapp.domain.models.Book
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import java.util.*


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun hiddenKeyboard(windowToken: IBinder, activity: Activity) {
    val imm = activity.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    imm.hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun TextView.setBookInfo(info: String, textView: TextView) {
    if (info.split(": ").get(1) == "null") {
        textView.hide()
    } else {
        textView.setText(info)
        textView.show()
    }
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}


fun AlertDialog.setUpViewAler(book: Book, bindingAlert: MaterialAlertBookBinding, isFavorite:Boolean = false) {

    bindingAlert.tvSubtitle.setText(book.title)

    val authorText = "Autor: " + (book.author_name?.joinToString(", ") ?: "a")
    val dateText = "Fecha publicación: " + book.first_publish_year.toString()
    val pageText = "Numero de paginas: " + book.number_of_pages_median.toString()
    val colaboradoresText = "Colaboradores: " + book.contributor?.joinToString(", ")
    val placesText = "Lugares de publicación: " + book.publish_place?.joinToString(
        ", "
    )
    val editorsText = "Editoriales: " + book.publisher?.joinToString(", ")
    val isbnText = "ISBN: " + book.isbn?.get(0).toString()
    val languageText = "Lenguajes: " + book.language?.joinToString(", ")
    val personsText = "Pensonajes: " + book.person?.joinToString(", ")

    bindingAlert.tvAuthor.setBookInfo(authorText, bindingAlert.tvAuthor)
    bindingAlert.tvDate.setBookInfo(dateText, bindingAlert.tvDate)
    bindingAlert.tvPagNumber.setBookInfo(pageText, bindingAlert.tvPagNumber)
    bindingAlert.tvContributors.setBookInfo(colaboradoresText, bindingAlert.tvContributors)
    bindingAlert.tvPublishPlaces.setBookInfo(placesText, bindingAlert.tvPublishPlaces)
    bindingAlert.tvPublisher.setBookInfo(editorsText, bindingAlert.tvPublisher)
    bindingAlert.tvIsbn.setBookInfo(isbnText, bindingAlert.tvIsbn)
    bindingAlert.tvLanguage.setBookInfo(languageText, bindingAlert.tvLanguage)
    bindingAlert.tvPersons.setBookInfo(personsText, bindingAlert.tvPersons)

    if (isFavorite){
        bindingAlert.fbDeleteFav.show()
    } else {
        bindingAlert.fbDeleteFav.hide()
    }

    val imgUrl = "https://covers.openlibrary.org/b/isbn/${book.isbn?.get(0)}-M.jpg"
    Picasso.get()
        .load(imgUrl)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.drawable.ic_image_not_supported)
        .into(bindingAlert.imgBook)
}

