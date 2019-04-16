package com.example.myawesomepetproject.details.di

import com.example.base.di.scope.PerFragment
import com.example.feed.di.PostsFeatureComponentApi
import com.example.myawesomepetproject.details.presentation.DetailsFragment
import com.example.user.di.UserFeatureComponentApi
import dagger.Component

@PerFragment
@Component(dependencies = [PostsFeatureComponentApi::class, UserFeatureComponentApi::class], modules = [DetailsModule::class])
interface DetailsComponent {
  fun inject(detailsFragment: DetailsFragment)
}