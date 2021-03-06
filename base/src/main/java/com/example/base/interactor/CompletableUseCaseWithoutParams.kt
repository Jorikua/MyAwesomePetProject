package com.example.base.interactor

import io.reactivex.Completable

abstract class CompletableUseCaseWithoutParams {

  protected abstract fun buildUseCaseObservable(): Completable

  fun execute(): Completable {
    return buildUseCaseObservable()
  }
}
