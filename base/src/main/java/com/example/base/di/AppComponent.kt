package com.example.base.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface AppComponent : AppComponentApi{
  
  companion object {
    @Volatile
    private lateinit var appComponent: AppComponent
    
    fun init(appComponent: AppComponent) {
      if (this::appComponent.isInitialized) {
        throw IllegalArgumentException("BaseAppComponent is already initialized.")
      }
      
      Companion.appComponent = appComponent
    }
    
    fun get(): AppComponent {
      if (!this::appComponent.isInitialized) {
        throw NullPointerException("BaseAppComponent is not initialized yet. Call init first.")
      }
      
      return appComponent
    }
  }
}