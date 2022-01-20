package com.guichristovao.appstartup.feature_one

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeatureOneViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    class Factory(
        private val gitHubRepository: GitHubRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeatureOneViewModel(gitHubRepository) as T
        }
    }

    val gitHubUser = MutableLiveData<GitHubUser>()

    val loading = MutableLiveData<Boolean>()
    val requestError = MutableLiveData<String?>()

    fun getUser(user: String?) {
        loading.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            gitHubRepository.getUser(user).let { response ->
                if (response.isSuccessful) {
                    gitHubUser.postValue(response.body())
                    requestError.postValue(null)
                    loading.postValue(false)
                } else {
                    onError("Error : ${response.message()} ")
                }
            }

            requestError.postValue("")
            loading.postValue(false)
        }
    }

    private fun onError(message: String) {
        requestError.postValue(message)
        loading.postValue(false)
    }
}
