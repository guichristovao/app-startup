package com.guichristovao.appstartup.profile.ui.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.profile.data.ProfileRepository
import com.guichristovao.appstartup.theme.ui.component.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val exceptionHandler: @JvmSuppressWildcards ExceptionHandler
) : ViewModel() {

    sealed class UiState {
        object Default : UiState()
        object Loading : UiState()
        data class Success(val user: User) : UiState()
        data class Error(val message: String?) : UiState()
    }

    private val _uiState = MutableLiveData<UiState>(UiState.Default)
    val uiState: LiveData<UiState>
        get() = _uiState

    private val coroutineHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = UiState.Error(throwable.message)
        exceptionHandler(throwable)
    }

    fun getUser(user: String?) {
        _uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO + coroutineHandler) {
            profileRepository.getUser(user).run {
                _uiState.postValue(UiState.Success(User(avatarUrl, name, login)))
            }
        }
    }
}
