package com.example.myawesomepetproject.posts.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.base.BasePresenter
import com.example.base.di.scope.PerFragment
import com.example.base.extensions.plusAssign
import com.example.feed.domain.interactor.GetPostsUseCase
import com.example.myawesomepetproject.posts.presentation.mapper.PostViewModelMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
@PerFragment
class PostsPresenter @Inject constructor(private val getPostsUseCase: GetPostsUseCase,
    private val postViewModelMapper: PostViewModelMapper) : BasePresenter<PostsView>() {
  
  fun loadPosts() {
    disposables += getPostsUseCase.execute()
        .map { postViewModelMapper.map(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          viewState.onLoadPostsSuccess(it)
        }, {
          Log.d(PostsPresenter::class.java.name, "Error", it)
          viewState.onLoadPostsFailure()
        })
  }
}