package com.farmtech.weighingmachine.ui.screen.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farmtech.weighingmachine.AuthRepository
import com.farmtech.weighingmachine.ui.components.utils.Toast
import com.farmtech.weighingmachine.ui.components.utils.isEmpty
import com.farmtech.weighingmachine.ui.components.utils.logMessage
import com.farmtech.weighingmachine.utils.GraphqlError
import com.farmtech.weighingmachine.utils.onMutation
import com.farmtech.weighingmachine.utils.optional
import com.farmtech.weighningmachine.LoginMutation
import com.farmtech.weighningmachine.type.LoginInput
import com.farmtech.weighningmachine.type.LoginType
import com.farmtech.weighningmachine.type.Platform
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class SignInViewState(
    val companyId: Int? = null,
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false
)

sealed class NavigationEvent {
    data object Success : NavigationEvent()
}

class SignInViewModel() : ViewModel() {
    private val _company_id = MutableStateFlow<Int?>(null)
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _state = MutableStateFlow(SignInViewState())
    private val _navigationEvent =
        MutableSharedFlow<NavigationEvent>() // Use MutableSharedFlow for events
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvent.asSharedFlow()

    val state: StateFlow<SignInViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(_company_id, _email, _password, _loading) { companyId, email, password, loading ->
                SignInViewState(
                    companyId,
                    email,
                    password,
                    loading
                )
            }.collect { _state.value = it }
        }
    }

    fun setCompany(companyId: Int?) {
        _company_id.value = companyId
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    private fun onLoginSuccess() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.Success)
        }
    }

    fun onSubmit() {
        _loading.value = true
        viewModelScope.launch {
            signIn(state = _state.value, onSuccess = {
                val response = it?.Login?.tokenResponse
              logMessage("${it?.Login?.tokenResponse?.token}")
                if (!isEmpty(response?.token)) {
                    Toast.successToast("Successfully sign in")
                    AuthRepository.signIn(response?.token ?: "")
                }
                onLoginSuccess()
            })
            _loading.value = false
        }
    }
}

private suspend fun signIn(state: SignInViewState, onSuccess: (LoginMutation.Data?) -> Unit = {}) {
    val input = LoginInput(
        user_email = state.email.optional(),
        login_type = LoginType.user_email.optional(),
        platform = Platform.mobile.optional(),
        password = state.password.optional(),
    )
    val loginMutation = LoginMutation(input.optional())

    onMutation(loginMutation,
        onSuccess = { onSuccess(it) },
        onError = { error ->
            (error as? GraphqlError.Messages)?.let {
                Toast.errorToast(message = error.message)
                logMessage("Errors: ${error.message}")
            }
//            when (it) {
//                is GraphqlError.Error -> {
//                    logMessage("Errors ${it.errors}")
//                }
//                is GraphqlError.Messages -> {
//                    logMessage("Messages ${it.message}")
//                }
//                else -> {
//                    logMessage("Exception $it")
//                }
//            }
        }
    )
}