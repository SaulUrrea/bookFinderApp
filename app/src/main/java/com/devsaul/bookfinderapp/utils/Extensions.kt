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
import androidx.fragment.app.Fragment
import java.util.*


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.disable() {
    isEnabled = false
}

fun View.enabled() {
    isEnabled = true
}

fun hiddenKeyboard(windowToken: IBinder,activity: Activity) {
    val imm = activity.getSystemService(
        Context.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    imm.hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun TextView.setBookInfo(info: String, textView: TextView) {
    if (info.split(": ").get(1) == "null"){
        textView.hide()
    }else{
        textView.setText(info)
        textView.show()
    }
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}


