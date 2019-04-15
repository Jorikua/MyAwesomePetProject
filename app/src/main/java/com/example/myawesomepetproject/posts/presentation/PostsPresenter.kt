package com.example.myawesomepetproject.posts.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.myawesomepetproject.base.extensions.plusAssign
import com.example.myawesomepetproject.di.scope.PerFragment
import com.example.myawesomepetproject.posts.domain.repository.PostsRepository
import com.example.myawesomepetproject.posts.presentation.mapper.PostViewModelMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
@PerFragment
class PostsPresenter @Inject constructor(private val repository: PostsRepository,
    private val postViewModelMapper: PostViewModelMapper): MvpPresenter<PostsView>() {
  
  private val disposables = CompositeDisposable()
  
  fun loadPosts() {
    disposables += repository.getPosts()
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
  
  override fun detachView(view: PostsView?) {
    super.detachView(view)
    disposables.clear()
  }
}