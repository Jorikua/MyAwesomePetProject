package com.example.myawesomepetproject.details.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.base.BasePresenter
import com.example.base.di.scope.PerFragment
import com.example.base.extensions.plusAssign
import com.example.feed.domain.interactor.GetFullPostUseCase
import com.example.myawesomepetproject.details.presentation.mapper.FullPostViewModelMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
@PerFragment
class DetailsPresenter @Inject constructor(
    private val getFullPostUseCase: GetFullPostUseCase,
    private val fullPostViewModelMapper: FullPostViewModelMapper
) : BasePresenter<DetailsView>() {
  
  fun loadDetails(postId: Int, userId: Int) {
    val params = GetFullPostUseCase.Params(userId, postId)
    disposables += getFullPostUseCase.execute(params)
        .map { fullPostViewModelMapper.map(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          viewState.onLoadDetailsSuccess(it)
        }, {
          Log.d(this::class.java.name, "Error", it)
          viewState.onLoadDetailsFailure()
        })
  }
}