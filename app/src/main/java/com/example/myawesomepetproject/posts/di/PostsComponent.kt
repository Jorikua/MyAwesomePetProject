package com.example.myawesomepetproject.posts.di

import com.example.base.di.scope.PerFragment
import com.example.feed.di.PostsFeatureComponentApi
import com.example.myawesomepetproject.posts.presentation.PostsFragment
import dagger.Component

@PerFragment
@Component(dependencies = [PostsFeatureComponentApi::class], modules = [PostsModule::class])
interface PostsComponent {
    fun inject(postsFragment: PostsFragment)
}