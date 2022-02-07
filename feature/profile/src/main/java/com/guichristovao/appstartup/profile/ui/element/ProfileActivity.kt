package com.guichristovao.appstartup.profile.ui.element

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.guichristovao.appstartup.profile.ui.state.ProfileViewModel
import com.guichristovao.appstartup.splash_screen.showSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        showSplashScreen { viewModel.uiState.value is ProfileViewModel.UiState.Default }
        super.onCreate(savedInstanceState)
        setContent { ProfileScreen() }

        getProfile()
    }

    private fun getProfile() {
        viewModel.getUser("guichristovao")
    }
}
