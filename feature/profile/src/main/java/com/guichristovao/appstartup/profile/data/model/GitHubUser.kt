package com.guichristovao.appstartup.profile.data.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class GitHubUser(
    val login: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    val name: String = ""
)
