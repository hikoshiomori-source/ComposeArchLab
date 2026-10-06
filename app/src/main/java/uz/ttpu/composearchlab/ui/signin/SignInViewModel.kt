package uz.ttpu.composearchlab.ui.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    // _uiState is private, so the state changes only here in the ViewModel.
    // the screen gets uiState and can only read it
    private val _uiState = mutableStateOf<SignInUiState>(SignInUiState.SignedOut)
    val uiState: State<SignInUiState>
        get() = _uiState

    fun onSignIn(email: String, password: String) {
        if (_uiState.value == SignInUiState.InProgress) return

        _uiState.value = SignInUiState.InProgress
        viewModelScope.launch {
            delay(1500)
            if (password == "kotlin123") {
                _uiState.value = SignInUiState.SignedIn(email)
            } else {
                _uiState.value = SignInUiState.Error("Wrong email or password")
            }
        }
    }
}
