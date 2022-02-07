package com.guichristovao.appstartup.profile.ui.element

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guichristovao.appstartup.profile.data.model.GitHubUser
import com.guichristovao.appstartup.profile.ui.state.ProfileViewModel
import com.guichristovao.appstartup.profile.ui.state.ProfileViewModel.UiState
import com.guichristovao.appstartup.theme.ui.component.ProfileCard
import com.guichristovao.appstartup.theme.ui.component.User
import com.guichristovao.appstartup.theme.ui.theme.AppStartupTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    AppStartupTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            when (val state = viewModel.uiState.observeAsState().value) {
                is UiState.Default -> ContentDefault()
                is UiState.Loading -> ContentLoading()
                is UiState.Success -> ContentSuccess(state)
                is UiState.Error -> ContentError(state)
            }
        }
    }
}

@Composable
private fun ContentDefault() {
    Text(
        text = "User profile not loaded yet",
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun ContentLoading() {
    CircularProgressIndicator(modifier = Modifier.size(40.dp))
}

@Composable
private fun ContentSuccess(state: UiState.Success) {
    with(state.user) {
        ProfileCard(
            user = User(avatarUrl, name, login)
        )
    }
}

@Composable
private fun ContentError(state: UiState.Error) {
    Text(
        text = "Error loading user profile: ${state.message}",
        color = MaterialTheme.colors.error
    )
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun ContentDefaultPreview() {
    AppStartupTheme {
        ContentDefault()
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun ContentLoadingPreview() {
    AppStartupTheme {
        ContentLoading()
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun ContentSuccessPreview() {
    AppStartupTheme {
        ContentSuccess(
            UiState.Success(
                GitHubUser(
                    login = "guichristovao",
                    name = "Guilherme de Sá Christovão"
                )
            )
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun ContentErrorPreview() {
    AppStartupTheme {
        ContentError(UiState.Error("Preview error message"))
    }
}