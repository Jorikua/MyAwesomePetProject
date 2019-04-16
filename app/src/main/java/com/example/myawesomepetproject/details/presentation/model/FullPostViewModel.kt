package com.example.myawesomepetproject.details.presentation.model

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import com.example.myawesomepetproject.R

class FullPostViewModel(val userId: Int = 0,
    val postId: Int = 0,
    val name: String = "",
    val username: String = "",
    val website: String = "",
    val numberOfComments: Int = 0,
    val title: String = "",
    val body: String = "") {
  
  fun getCorrectName(context: Context): SpannableString {
    val rightName = if (name.isEmpty()) "-" else name
    val fullText = context.getString(R.string.format_name, rightName)
    return decorateText(fullText, rightName)
  }
  
  fun getCorrectUsername(context: Context): SpannableString {
    val rightUsername = if (username.isEmpty()) "-" else username
    val fullText = context.getString(R.string.format_username, rightUsername)
    return decorateText(fullText, rightUsername)
  }
  
  private fun decorateText(fullText: String, textToDecorate: String): SpannableString {
    return SpannableString(fullText).apply {
      val startIndex = fullText.indexOf(textToDecorate)
      setSpan(ForegroundColorSpan(Color.BLACK), startIndex, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
  }
  
  fun getEmptyWebsite(context: Context): SpannableString {
    val text = "-"
    val fullText = context.getString(R.string.format_website, text)
    return decorateText(fullText, text)
  }
  
  inline fun getWebsiteText(context: Context, crossinline onClick: () -> Unit): SpannableString {
    val fullText = context.getString(R.string.format_website, website)
    val spannableString = SpannableString(fullText)
    val clickableSpan = object : ClickableSpan() {
      override fun onClick(widget: View) {
        onClick.invoke()
      }
    }
    val startIndex = fullText.indexOf(website)
    spannableString.setSpan(clickableSpan, startIndex, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
  }
}