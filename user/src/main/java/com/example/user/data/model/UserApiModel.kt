package com.example.user.data.model

import com.google.gson.annotations.SerializedName

class UserApiModel(@SerializedName("id") val id: Int,
                   @SerializedName("name") val name: String,
                   @SerializedName("username") val username: String,
                   @SerializedName("website") val website: String)