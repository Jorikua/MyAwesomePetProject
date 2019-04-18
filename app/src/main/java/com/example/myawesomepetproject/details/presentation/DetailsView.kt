package com.example.myawesomepetproject.details.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.myawesomepetproject.details.presentation.model.FullPostViewModel

interface DetailsView : MvpView{
  fun onLoadDetailsSuccess(fullPostViewModel: FullPostViewModel)
  @StateStrategyType(OneExecutionStateStrategy::class)
  fun onLoadDetailsFailure()
}