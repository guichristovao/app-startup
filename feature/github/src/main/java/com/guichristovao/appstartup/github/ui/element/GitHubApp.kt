package com.guichristovao.appstartup.github.ui.element

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guichristovao.appstartup.github.ui.state.GitHubViewModel
import com.guichristovao.appstartup.theme.ui.component.ProfileCard
import com.guichristovao.appstartup.theme.ui.component.User
import com.guichristovao.appstartup.theme.ui.theme.AppStartupTheme

@Composable
fun GitHubApp(
    viewModel: GitHubViewModel = viewModel()
) {
    AppStartupTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            val user = viewModel.gitHubUser.observeAsState()
            user.value?.run {
                ProfileCard(
                    user = User(avatarUrl, name, login)
                )
            }
        }
    }
}
