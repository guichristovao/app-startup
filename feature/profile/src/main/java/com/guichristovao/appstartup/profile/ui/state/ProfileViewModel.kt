package com.guichristovao.appstartup.profile.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guichristovao.appstartup.network.ExceptionHandler
import com.guichristovao.appstartup.profile.data.source.ProfileRepository
import com.guichristovao.appstartup.theme.ui.component.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val defaultProfileRepository: ProfileRepository,
    private val exceptionHandler: @JvmSuppressWildcards ExceptionHandler
) : ViewModel() {

    sealed class UiState {
        object Default : UiState()
        object Loading : UiState()
        data class Success(val user: User) : UiState()
        data class Error(val message: String?) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Default)
    val uiState: StateFlow<UiState> = _uiState

    private val coroutineHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = UiState.Error(throwable.message)
        exceptionHandler(throwable)
    }

    fun getUser(user: String?) {
        viewModelScope.launch(coroutineHandler) {
            _uiState.value = UiState.Loading

            defaultProfileRepository.getUser(user).run {
                _uiState.value = UiState.Success(User(avatarUrl, name, login))
            }
        }
    }
}
