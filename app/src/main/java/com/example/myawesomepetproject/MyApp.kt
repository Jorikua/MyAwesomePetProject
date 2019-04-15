package com.example.myawesomepetproject

import android.app.Application
import com.example.base.di.AppComponent
import com.example.base.di.ContextModule
import com.example.base.di.DaggerAppComponent

class MyApp : Application() {
  
  override fun onCreate() {
    super.onCreate()
  
    AppComponent.init(
        DaggerAppComponent.builder()
        .contextModule(ContextModule(this))
        .build())
  }
}