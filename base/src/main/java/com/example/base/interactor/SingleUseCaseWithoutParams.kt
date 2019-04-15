package com.example.base.interactor

import io.reactivex.Single

abstract class SingleUseCaseWithoutParams<T> {

  protected abstract fun buildUseCaseObservable(): Single<T>

  fun execute(): Single<T> {
    return buildUseCaseObservable()
  }
}