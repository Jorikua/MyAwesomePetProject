package com.example.myawesomepetproject.posts.di

import com.arellomobile.mvp.MvpPresenter
import com.example.base.di.scope.PerFragment
import com.example.myawesomepetproject.posts.presentation.PostsPresenter
import com.example.myawesomepetproject.posts.presentation.PostsView
import dagger.Binds
import dagger.Module

@Module
abstract class PostsModule {

    @Binds
    @PerFragment
    abstract fun providePresenter(impl: PostsPresenter): MvpPresenter<PostsView>
}