package com.example.base.extensions

import android.view.View

fun View.onClick(l: (v: View?) -> Unit) {
  setOnClickListener(l)
}