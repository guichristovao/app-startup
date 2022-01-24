package com.guichristovao.appstartup.github.ui.state

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guichristovao.appstartup.github.data.GitHubRepository
import com.guichristovao.appstartup.github.data.model.GitHubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    val gitHubUser = MutableLiveData<GitHubUser?>()
    val loading = MutableLiveData(true)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.v("app-startup", "$throwable")
    }

    fun getUser(user: String?) {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val result = gitHubRepository.getUser(user)
            gitHubUser.postValue(result)
            loading.postValue(false)
        }
    }
}
