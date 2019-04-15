package com.example.myawesomepetproject.main

import android.os.Bundle
import androidx.fragment.app.transaction
import com.example.base.MvpAppCompatActivity
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.posts.presentation.PostsFragment

class MainActivity : MvpAppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    supportFragmentManager.transaction {
      replace(R.id.mainContainer, PostsFragment(), PostsFragment::class.java.name)
    }
  }
}
