package com.example.myawesomepetproject.details.presentation.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.example.base.di.scope.PerFragment
import com.example.myawesomepetproject.details.presentation.adapter.model.detailsHeaderEpoxyView
import com.example.myawesomepetproject.details.presentation.model.FullPostViewModel
import javax.inject.Inject

@PerFragment
class DetailsController @Inject constructor(): TypedEpoxyController<FullPostViewModel>() {
  
  var listener: Listener? = null
  
  override fun buildModels(data: FullPostViewModel) {
    detailsHeaderEpoxyView {
      id(data.postId)
      fullPost(data)
      websiteClick { listener?.onWebsiteClick(it) }
    }
  }
  
  interface Listener {
    fun onWebsiteClick(website: String)
  }
}