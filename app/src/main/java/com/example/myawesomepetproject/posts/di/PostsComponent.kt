package com.example.myawesomepetproject.posts.di

import com.example.myawesomepetproject.di.scope.PerFragment
import com.example.myawesomepetproject.main.di.MainComponent
import com.example.myawesomepetproject.posts.presentation.PostsFragment
import dagger.Component

@PerFragment
@Component(dependencies = [MainComponent::class], modules = [PostsModule::class])
interface PostsComponent {
  fun inject(postsFragment: PostsFragment)
}