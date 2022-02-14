package com.guichristovao.appstartup.profile.ui.state

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.profile.MainCoroutineScopeRule
import com.guichristovao.appstartup.profile.data.source.FakeProfileRepository
import com.guichristovao.appstartup.profile.data.source.ProfileRepository
import com.guichristovao.appstartup.profile.ui.state.ProfileViewModel.UiState
import com.guichristovao.appstartup.theme.ui.component.User
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var repository: ProfileRepository
    private lateinit var exceptionHandler: ExceptionHandler

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineScope = MainCoroutineScopeRule()

    @Before
    fun setupViewModel() {
        repository = FakeProfileRepository()
        exceptionHandler = mockk(relaxed = true)
        viewModel = ProfileViewModel(repository, exceptionHandler)
    }

    @Test
    fun createViewModel_setsDefaultState() {
        assertThat(viewModel.uiState.value).isEqualTo(UiState.Default)
    }

    @Test
    fun getUser_setsSuccessState() = coroutineScope.runBlockingTest {
        val expectedUser = User("fakeAvatarUrl", "fakeName", "fakeUser")

        viewModel.uiState.test {
            viewModel.getUser("fakeUser")

            assertThat(awaitItem()).isEqualTo(UiState.Default)
            assertThat(awaitItem()).isEqualTo(UiState.Loading)
            assertThat(awaitItem()).isEqualTo(UiState.Success(expectedUser))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun requestException_getUser_setsErrorState() = coroutineScope.runBlockingTest {
        viewModel.uiState.test {
            viewModel.getUser(null)

            assertThat(awaitItem()).isEqualTo(UiState.Default)
            assertThat(awaitItem()).isEqualTo(UiState.Loading)
            assertThat(awaitItem()).isEqualTo(UiState.Error("fakeMessage"))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun requestException_getUser_invokeExceptionHandler() = coroutineScope.runBlockingTest {
        val slot = slot<Exception>()
        justRun { exceptionHandler(capture(slot)) }

        viewModel.getUser(null)

        verify { exceptionHandler(any<RuntimeException>()) }
        assertThat(slot.captured.message).isEqualTo("fakeMessage")
    }
}
