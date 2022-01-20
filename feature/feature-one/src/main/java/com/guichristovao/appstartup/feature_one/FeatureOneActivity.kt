package com.guichristovao.appstartup.feature_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guichristovao.appstartup.theme.AppStartupTheme

class FeatureOneActivity : ComponentActivity() {

    private lateinit var viewModel: FeatureOneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setScreenContent()

        getGitHubUser()
    }

    private fun setupViewModel() {
        val repository = GitHubRepository(
            FeatureOne.serviceCreator.create(GitHubService::class.java) as GitHubService
        )
        viewModel = ViewModelProvider(
            this,
            FeatureOneViewModel.Factory(repository)
        )[FeatureOneViewModel::class.java]
    }

    private fun setScreenContent() {
        setContent {
            AppStartupTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }

    private fun getGitHubUser() {
        viewModel.getUser("guichristovao")
    }
}

@Composable
fun Greeting(
    viewModel: FeatureOneViewModel = viewModel()
) {
    val name = viewModel.gitHubUser.observeAsState()
    name.value?.let {
        Text(text = "Hello ${it.name}!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppStartupTheme {
        Greeting()
    }
}
