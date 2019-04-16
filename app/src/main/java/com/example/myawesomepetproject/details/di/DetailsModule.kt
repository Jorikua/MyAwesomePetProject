package com.example.myawesomepetproject.details.di

import com.arellomobile.mvp.MvpPresenter
import com.example.base.di.scope.PerFragment
import com.example.myawesomepetproject.details.presentation.DetailsPresenter
import com.example.myawesomepetproject.details.presentation.DetailsView
import dagger.Binds
import dagger.Module

@Module
abstract class DetailsModule {
  
  @Binds
  @PerFragment
  abstract fun providePresenter(impl: DetailsPresenter): MvpPresenter<DetailsView>
}