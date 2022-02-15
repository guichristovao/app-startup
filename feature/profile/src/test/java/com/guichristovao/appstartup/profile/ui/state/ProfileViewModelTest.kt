package com.guichristovao.appstartup.profile.ui.state

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.guichristovao.appstartup.network.ExceptionHandler
import com.guichristovao.appstartup.profile.data.source.FakeProfileRepository
import com.guichristovao.appstartup.profile.data.source.ProfileRepository
import com.guichristovao.appstartup.profile.ui.state.ProfileViewModel.UiState
import com.guichristovao.appstartup.theme.ui.component.User
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var repository: ProfileRepository
    private lateinit var exceptionHandler: ExceptionHandler

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        repository = FakeProfileRepository()
        exceptionHandler = mockk(relaxed = true)
        viewModel = ProfileViewModel(repository, exceptionHandler)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun createViewModel_setsDefaultState() {
        assertThat(viewModel.uiState.value).isEqualTo(UiState.Default)
    }

    @Test
    fun getUser_setsSuccessState() = runTest {
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
    fun requestException_getUser_setsErrorState() = runTest {
        viewModel.uiState.test {
            viewModel.getUser(null)

            assertThat(awaitItem()).isEqualTo(UiState.Default)
            assertThat(awaitItem()).isEqualTo(UiState.Loading)
            assertThat(awaitItem()).isEqualTo(UiState.Error("fakeMessage"))
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun requestException_getUser_invokeExceptionHandler() {
        val slot = slot<Exception>()

        runTest {
            justRun { exceptionHandler(capture(slot)) }

            viewModel.getUser(null)
        }

        verify { exceptionHandler(any<RuntimeException>()) }
        assertThat(slot.captured.message).isEqualTo("fakeMessage")
    }
}
