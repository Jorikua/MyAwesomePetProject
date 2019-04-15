package com.example.myawesomepetproject.main

import android.os.Bundle
import androidx.fragment.app.transaction
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.base.MvpAppCompatActivity
import com.example.myawesomepetproject.di.app.AppComponent
import com.example.myawesomepetproject.main.di.DaggerMainComponent
import com.example.myawesomepetproject.main.di.MainComponent
import com.example.myawesomepetproject.posts.presentation.PostsFragment

class MainActivity : MvpAppCompatActivity() {
  
  lateinit var component: MainComponent

  override fun onCreate(savedInstanceState: Bundle?) {
    injectComponent()
    
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    supportFragmentManager.transaction {
      replace(R.id.mainContainer, PostsFragment(), PostsFragment::class.java.name)
    }
  }
  
  private fun injectComponent() {
    component = DaggerMainComponent.builder()
        .appComponent(AppComponent.get())
        .build()
    component.inject(this)
  }
}
