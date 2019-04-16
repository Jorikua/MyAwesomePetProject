package com.example.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.extensions.inflate

abstract class InjectableFragment<T> : MvpAppCompatFragment() {
  
  var component: T? = null
  
  abstract val layoutId: Int
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return container?.inflate(layoutId)
  }
  
  override fun onAttach(context: Context?) {
    component = initializeComponent()
    inject()
    super.onAttach(context)
  }
  
  abstract fun initializeComponent(): T
  abstract fun inject()
}