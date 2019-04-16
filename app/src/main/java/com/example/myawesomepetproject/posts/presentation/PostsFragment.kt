package com.example.myawesomepetproject.posts.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.transaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.base.InjectableFragment
import com.example.base.extensions.onClick
import com.example.base.extensions.toast
import com.example.feed.di.postsFeatureComponent
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.details.presentation.DetailsFragment
import com.example.myawesomepetproject.posts.di.DaggerPostsComponent
import com.example.myawesomepetproject.posts.di.PostsComponent
import com.example.myawesomepetproject.posts.presentation.adapter.PostsController
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.android.synthetic.main.layout_something_went_wrong.*
import javax.inject.Inject

class PostsFragment : InjectableFragment<PostsComponent>(), PostsView {
  override val layoutId: Int = R.layout.fragment_posts
  
  @Inject
  @InjectPresenter
  lateinit var presenter: PostsPresenter
  
  @ProvidePresenter
  fun providePresenter(): PostsPresenter {
    return presenter
  }
  
  @Inject
  lateinit var postsController: PostsController
  
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
    override fun onPostClick(postViewModel: PostViewModel) {
      fragmentManager?.transaction {
        addToBackStack(null)
        setCustomAnimations(R.animator.slide_in_right, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_out_right)
        add(R.id.mainContainer, DetailsFragment.newInstance(postViewModel), DetailsFragment::class.java.name)
      }
    }
  }
  
  override fun onLoadPostsSuccess(list: List<PostViewModel>) {
    postsController.setData(list)
  }
  
  override fun onLoadPostsFailure() {
    when (postsController.currentData == null || postsController.currentData?.isEmpty() == true) {
      true -> postsSomethingWentWrongPlaceholder.isVisible = true
      false -> toast(R.string.something_went_wrong)
    }
  }
  
  override fun initializeComponent(): PostsComponent {
    return DaggerPostsComponent.builder()
        .postsFeatureComponentApi(postsFeatureComponent)
        .build()
  }
  
  override fun inject() {
    component?.inject(this)
  }
}