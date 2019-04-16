package com.example.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<View: MvpView>: MvpPresenter<View>() {
  
  protected val disposables = CompositeDisposable()
  
  override fun detachView(view: View) {
    super.detachView(view)
    disposables.clear()
  }
}