package com.asif.tmdb.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun logE(tag: String, message: String) {
    Log.e(tag, message)
}

fun logD(tag: String, message: String) {
    Log.d(tag, message)
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}