package com.guichristovao.appstartup.profile.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.profile.data.source.DefaultProfileRepository
import com.guichristovao.appstartup.theme.ui.component.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val defaultProfileRepository: DefaultProfileRepository,
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
            // Intentionally delays the request, so we can take a better glimpse of the view's
            // loading skeleton
            delay(3000)
            defaultProfileRepository.getUser(user).run {
                _uiState.value = UiState.Success(User(avatarUrl, name, login))
            }
        }
    }
}
