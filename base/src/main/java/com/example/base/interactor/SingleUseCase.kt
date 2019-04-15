package com.example.base.interactor

import io.reactivex.Single

abstract class SingleUseCase<T, in Params> {
  
  protected abstract fun buildUseCaseObservable(params: Params): Single<T>
  
  fun execute(params: Params): Single<T> {
    return buildUseCaseObservable(params)
  }
}