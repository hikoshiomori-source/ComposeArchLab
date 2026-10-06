package uz.ttpu.composearchlab.ui.signin

// enum cannot keep data, but here Error needs a message and SignedIn needs an email,
// so sealed interface is better
sealed interface SignInUiState {

    data object SignedOut : SignInUiState

    data object InProgress : SignInUiState

    data class Error(val message: String) : SignInUiState

    data class SignedIn(val email: String) : SignInUiState
}
