package com.kotlingithubapiapplication.activity.model

import com.google.gson.annotations.SerializedName

open class Repository(

    @SerializedName("name")
    val username: String? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("forks")
    val forks: Int? = null,

    @SerializedName("watchers_count")
    val watchers: Int? = null
)
