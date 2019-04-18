package com.example.myawesomepetproject.posts.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel

interface PostsView: MvpView {
  fun onLoadPostsSuccess(list: List<PostViewModel>)
  @StateStrategyType(OneExecutionStateStrategy::class)
  fun onLoadPostsFailure()
}