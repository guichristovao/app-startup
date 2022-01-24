package com.guichristovao.appstartup.github.ui.element

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.guichristovao.appstartup.github.ui.state.GitHubViewModel
import com.guichristovao.appstartup.splash_screen.showSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubActivity : ComponentActivity() {

    private val viewModel: GitHubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        showSplashScreen { viewModel.loading.value ?: false }
        super.onCreate(savedInstanceState)
        setContent { GitHubApp() }

        getGitHubUser()
    }

    private fun getGitHubUser() {
        viewModel.getUser("guichristovao")
    }
}
