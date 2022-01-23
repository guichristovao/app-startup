package com.guichristovao.appstartup.github.data.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class GitHubUser(
    val login: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    val url: String = "",
    val name: String = ""
)
