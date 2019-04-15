package com.example.base.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyHolder

open class KotlinHolder: EpoxyHolder() {
  
  lateinit var view: View
  
  override fun bindView(itemView: View) {
    view = itemView
  }
}