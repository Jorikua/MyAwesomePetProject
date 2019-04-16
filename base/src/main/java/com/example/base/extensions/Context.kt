package com.example.base.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

val HTTP_PREFIX = "http://"
val HTTPS_PREFIX = "https://"

fun Context.toast(@StringRes res: Int, length: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(this, res, length).show()
}

fun Context.browse(url: String, isWebUrl: Boolean = true) {
  var innerUrl = url
  if (isWebUrl && !url.startsWith(HTTP_PREFIX) && !url.startsWith(HTTPS_PREFIX)) {
    innerUrl = HTTP_PREFIX + url
  }
  try {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .addDefaultShareMenuItem()
        .setToolbarColor(Color.BLACK)
        .build()
        .launchUrl(this, innerUrl.toUri())
  } catch (e: java.lang.Exception) {
    openBrowser(innerUrl.toUri())
  }
}

fun Context.openBrowser(uri: Uri) {
  try {
    val i = Intent(Intent.ACTION_VIEW, uri)
    startActivity(i)
  } catch (e: Exception) {
    e.printStackTrace()
  }
}