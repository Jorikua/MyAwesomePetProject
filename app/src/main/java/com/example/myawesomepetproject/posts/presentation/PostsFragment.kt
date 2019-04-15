package com.example.myawesomepetproject.posts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.transaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.base.InjectableFragment
import com.example.myawesomepetproject.base.extensions.inflate
import com.example.myawesomepetproject.base.extensions.onClick
import com.example.myawesomepetproject.base.extensions.toast
import com.example.myawesomepetproject.main.MainActivity
import com.example.myawesomepetproject.posts.di.DaggerPostsComponent
import com.example.myawesomepetproject.posts.di.PostsComponent
import com.example.myawesomepetproject.posts.presentation.adapter.PostsController
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.layout_something_went_wrong.*
import javax.inject.Inject

class PostsFragment : InjectableFragment<PostsComponent>(), PostsView {
  
  @Inject
  @InjectPresenter
  lateinit var presenter: PostsPresenter
  
  @ProvidePresenter
  fun providePresenter(): PostsPresenter {
    return presenter
  }
  
  @Inject
  lateinit var postsController: PostsController
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return container?.inflate(R.layout.fragment_posts)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initRv()
    initListeners()
  }
  
  override fun onStart() {
    super.onStart()
    presenter.loadPosts()
  }
  
  private fun initRv() {
    with(postsRv) {
      adapter = postsController.adapter
      layoutManager = LinearLayoutManager(context)
      setController(postsController)
      addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
  }
  
  private fun initListeners() {
    postsController.postsListener = postsListener
    
    wentWrongBtnRetry.onClick {
      postsSomethingWentWrongPlaceholder.isGone = true
      presenter.loadPosts()
    }
  }
  
  private val postsListener = object : PostsController.PostsListener {
    override fun onPostClick(id: Int) {
      fragmentManager?.transaction {
//        add(R.id.mainContainer, )
      }
    }
  }
  
  override fun onLoadPostsSuccess(list: List<PostViewModel>) {
    postsController.setData(list)
  }
  
  override fun onLoadPostsFailure() {
    when(postsController.currentData == null || postsController.currentData?.isEmpty() == true) {
      true -> postsSomethingWentWrongPlaceholder.isVisible = true
      false -> toast(R.string.something_went_wrong)
    }
  }
  
  override fun initializeComponent(): PostsComponent {
    return DaggerPostsComponent.builder()
        .mainComponent((activity as MainActivity).component)
        .build()
  }
  
  override fun inject() {
    component?.inject(this)
  }
}