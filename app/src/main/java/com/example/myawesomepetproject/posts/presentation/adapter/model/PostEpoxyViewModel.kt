package com.example.myawesomepetproject.posts.presentation.adapter.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.posts.presentation.model.PostViewModel
import kotlinx.android.synthetic.main.list_item_post.view.*

@EpoxyModelClass(layout = R.layout.list_item_post)
abstract class PostEpoxyViewModel : EpoxyModelWithHolder<PostEpoxyViewModel.Holder>(){
  
  @EpoxyAttribute
  lateinit var postViewModel: PostViewModel
  
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var postClick: (() -> Unit)? = null
  
  override fun bind(holder: Holder) {
    with(holder.view) {
      itemPostTvBody.text = postViewModel.body
      itemPostTvTitle.text = postViewModel.title
      setOnClickListener {
        postClick?.invoke()
      }
    }
  }
  
  class Holder : com.example.base.epoxy.KotlinHolder()
}