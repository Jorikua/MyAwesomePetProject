package com.example.myawesomepetproject.main.di

import com.example.myawesomepetproject.di.app.AppComponent
import com.example.myawesomepetproject.di.scope.PerActivity
import com.example.myawesomepetproject.main.MainActivity
import dagger.Component
import retrofit2.Retrofit

@PerActivity
@Component(dependencies = [AppComponent::class])
interface MainComponent {
  fun retrofit(): Retrofit
  
  fun inject(activity: MainActivity)
}