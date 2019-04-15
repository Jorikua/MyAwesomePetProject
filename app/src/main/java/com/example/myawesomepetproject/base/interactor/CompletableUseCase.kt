package com.example.myawesomepetproject.base.interactor

import io.reactivex.Completable

abstract class CompletableUseCase<in Params> {

  protected abstract fun buildUseCaseObservable(params: Params): Completable

  fun execute(params: Params): Completable {
    return buildUseCaseObservable(params)
  }

}