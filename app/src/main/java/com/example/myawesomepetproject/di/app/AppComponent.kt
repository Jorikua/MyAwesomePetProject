package com.example.myawesomepetproject.di.app

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface AppComponent {
  fun retrofit(): Retrofit
  
  companion object {
    @Volatile
    private lateinit var appComponent: AppComponent
    
    fun init(appComponent: AppComponent) {
      if (this::appComponent.isInitialized) {
        throw IllegalArgumentException("BaseAppComponent is already initialized.")
      }
      
      this.appComponent = appComponent
    }
    
    fun get(): AppComponent {
      if (!this::appComponent.isInitialized) {
        throw NullPointerException("BaseAppComponent is not initialized yet. Call init first.")
      }
      
      return appComponent
    }
  }
}