package com.guichristovao.appstartup.profile.ui.element

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
            color = MaterialTheme.colors.primary,
            modifier = Modifier.fillMaxSize()
        ) {
            when (val state = viewModel.uiState.observeAsState().value) {
                is UiState.Default, UiState.Loading -> ContentLoading()
                is UiState.Success -> ContentSuccess(state.user)
                is UiState.Error -> ContentError(state)
            }
        }
    }
}

@Composable
private fun ContentLoading() = ContentSuccess(null)

@Composable
private fun ContentSuccess(user: User?) {
    ProfileCard(user)
}

@Composable
private fun ContentError(state: UiState.Error) {
    Text(
        text = "Error loading user profile: ${state.message}",
        color = MaterialTheme.colors.error
    )
}

@Preview("Loading - default")
@Preview("Loading - dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Loading - large font", fontScale = 2f)
@Composable
private fun ContentLoadingPreview() {
    AppStartupTheme {
        ContentLoading()
    }
}

@Preview("Success - default")
@Preview("Success - dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Success - large font", fontScale = 2f)
@Composable
private fun ContentSuccessPreview() {
    AppStartupTheme {
        ContentSuccess(
            User(
                name = "Guilherme de Sá Christovão",
                login = "guichristovao"
            )
        )
    }
}

@Preview("Error - default")
@Preview("Error - dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Error - large font", fontScale = 2f)
@Composable
private fun ContentErrorPreview() {
    AppStartupTheme {
        ContentError(UiState.Error("Preview error message"))
    }
}