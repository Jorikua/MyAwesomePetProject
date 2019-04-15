package com.example.myawesomepetproject

import android.app.Application
import com.example.myawesomepetproject.di.app.AppComponent
import com.example.myawesomepetproject.di.app.ContextModule
import com.example.myawesomepetproject.di.app.DaggerAppComponent

class MyApp : Application() {
  
  override fun onCreate() {
    super.onCreate()
  
    AppComponent.init(
        DaggerAppComponent.builder()
        .contextModule(ContextModule(this))
        .build())
  }
}