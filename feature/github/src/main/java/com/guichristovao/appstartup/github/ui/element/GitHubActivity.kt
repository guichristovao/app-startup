package com.guichristovao.appstartup.github.ui.element

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.guichristovao.appstartup.github.Injection
import com.guichristovao.appstartup.github.ui.state.GitHubViewModel
import com.guichristovao.appstartup.splash_screen.showSplashScreen

class GitHubActivity : ComponentActivity() {

    private val viewModel: GitHubViewModel by viewModels {
        Injection.provideGitHubVMFactory(
            Injection.provideGitHubRepository(
                Injection.provideGitHubService()
            )
        )
    }

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
