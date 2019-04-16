package com.example.myawesomepetproject.details.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.base.InjectableFragment
import com.example.base.extensions.browse
import com.example.base.extensions.onClick
import com.example.base.extensions.toast
import com.example.feed.di.postsFeatureComponent
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.details.di.DaggerDetailsComponent
import com.example.myawesomepetproject.details.di.DetailsComponent
import com.example.myawesomepetproject.details.presentation.adapter.DetailsController
import com.example.myawesomepetproject.details.presentation.model.FullPostViewModel
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import com.example.user.di.userFeatureComponent
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

const val postKey = "post"

class DetailsFragment : InjectableFragment<DetailsComponent>(), DetailsView {
  override val layoutId: Int = R.layout.fragment_details

  @Inject
  @InjectPresenter
  lateinit var presenter: DetailsPresenter

  @ProvidePresenter
  fun providePresenter(): DetailsPresenter {
    return presenter
  }
  
  @Inject
  lateinit var detailsController: DetailsController
  
  companion object {
    fun newInstance(postViewModel: PostViewModel): DetailsFragment {
      return DetailsFragment().apply {
        arguments = bundleOf(postKey to postViewModel)
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val postViewModel = arguments?.getParcelable<PostViewModel>(postKey) ?: return
    initRv()
    initListeners()
    setData(FullPostViewModel(postViewModel.userId, postViewModel.id,
        title = postViewModel.title,
        body = postViewModel.body))
  }

  override fun onStart() {
    super.onStart()
    val postViewModel = arguments?.getParcelable<PostViewModel>(postKey) ?: return
    presenter.loadDetails(postViewModel.id, postViewModel.userId)
  }
  
  private fun initRv() {
    with(detailsRv) {
      adapter = detailsController.adapter
      layoutManager = LinearLayoutManager(context)
      setController(detailsController)
    }
  }
  
  private fun initListeners() {
    detailsIvBack.onClick {
      activity?.onBackPressed()
    }
    
    detailsController.listener = detailsControllerListener
  }
  
  private val detailsControllerListener = object : DetailsController.Listener {
    override fun onWebsiteClick(website: String) {
      activity?.browse(website)
    }
  }
  
  private fun setData(fullPostViewModel: FullPostViewModel) {
    detailsController.setData(fullPostViewModel)
  }
  
  override fun inject() {
    component?.inject(this)
  }
  
  override fun initializeComponent(): DetailsComponent {
    return DaggerDetailsComponent.builder()
        .postsFeatureComponentApi(postsFeatureComponent)
        .userFeatureComponentApi(userFeatureComponent)
        .build()
  }
  
  override fun onLoadDetailsSuccess(fullPostViewModel: FullPostViewModel) {
    setData(fullPostViewModel)
  }
  
  override fun onLoadDetailsFailure() {
    toast(R.string.something_went_wrong)
  }
}