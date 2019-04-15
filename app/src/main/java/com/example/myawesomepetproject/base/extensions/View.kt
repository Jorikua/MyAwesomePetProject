package com.example.myawesomepetproject.base.extensions

import android.view.View

fun View.onClick(l: (v: View?) -> Unit) {
  setOnClickListener(l)
}