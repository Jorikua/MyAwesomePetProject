package com.example.myawesomepetproject.base

import android.content.Context

abstract class InjectableFragment<T> : MvpAppCompatFragment() {
  
  var component: T? = null
  
  override fun onAttach(context: Context?) {
    component = initializeComponent()
    inject()
    super.onAttach(context)
  }
  
  abstract fun initializeComponent(): T
  abstract fun inject()
}