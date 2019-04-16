package com.example.myawesomepetproject.details.presentation.adapter.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.base.epoxy.KotlinHolder
import com.example.myawesomepetproject.BuildConfig
import com.example.myawesomepetproject.R
import com.example.myawesomepetproject.details.presentation.model.FullPostViewModel
import kotlinx.android.synthetic.main.list_item_details_header.view.*

@EpoxyModelClass(layout = R.layout.list_item_details_header)
abstract class DetailsHeaderEpoxyViewModel : EpoxyModelWithHolder<DetailsHeaderEpoxyViewModel.Holder>() {
  
  private val avatarSize = 150
  
  @EpoxyAttribute
  lateinit var fullPost: FullPostViewModel
  
  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var websiteClick: ((String) -> Unit)? = null
  
  override fun bind(holder: Holder) {
    with(holder.view) {
      Glide.with(itemDetailsHeaderIvAvatar)
          .load("${BuildConfig.AVATAR}/$avatarSize/${fullPost.userId}")
          .placeholder(R.drawable.avatar_placeholder)
          .centerCrop()
          .into(itemDetailsHeaderIvAvatar)
      
      itemDetailsHeaderTvName.text = fullPost.getCorrectName(context)
      itemDetailsHeaderTvUsername.text = fullPost.getCorrectUsername(context)
      
      with(itemDetailsHeaderTvWebsite) {
        text = if (fullPost.website.isEmpty())
          fullPost.getEmptyWebsite(context)
        else
          fullPost.getWebsiteText(context) {
            websiteClick?.invoke(fullPost.website)
          }
      }
      
      itemDetailsHeaderTvTitle.text = fullPost.title
      itemDetailsHeaderTvComments.text = context.getString(R.string.format_comments, fullPost.numberOfComments)
      itemDetailsHeaderTvBody.text = fullPost.body
    }
  }
  
  class Holder : KotlinHolder()
}