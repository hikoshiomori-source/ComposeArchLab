package uz.ttpu.composearchlab.ui.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    onSignIn: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (uiState) {
            is SignInUiState.SignedIn -> Text("Welcome, ${uiState.email}")

            SignInUiState.SignedOut, SignInUiState.InProgress, is SignInUiState.Error -> {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(
                    onClick = { onSignIn(email, password) },
                    enabled = uiState != SignInUiState.InProgress
                ) {
                    Text("Sign in")
                }
                if (uiState == SignInUiState.InProgress) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun SignInRoute() {
    val viewModel: SignInViewModel = viewModel()
    val uiState = viewModel.uiState.value
    val snackbarHostState = remember { SnackbarHostState() }

    // task 8: after rotation the snackbar came back again, because the ViewModel did not
    // die and the state was still Error, so this effect started one more time
    // task 9: onErrorShown() makes the state SignedOut again, so the message is shown once
    LaunchedEffect(uiState) {
        if (uiState is SignInUiState.Error) {
            snackbarHostState.showSnackbar(uiState.message)
            viewModel.onErrorShown()
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        SignInScreen(uiState, viewModel::onSignIn, Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun SignInSignedOutPreview() {
    SignInScreen(uiState = SignInUiState.SignedOut, onSignIn = { _, _ -> })
}

@Preview(showBackground = true)
@Composable
fun SignInInProgressPreview() {
    SignInScreen(uiState = SignInUiState.InProgress, onSignIn = { _, _ -> })
}

@Preview(showBackground = true)
@Composable
fun SignInErrorPreview() {
    SignInScreen(uiState = SignInUiState.Error("Wrong email or password"), onSignIn = { _, _ -> })
}

@Preview(showBackground = true)
@Composable
fun SignInSignedInPreview() {
    SignInScreen(uiState = SignInUiState.SignedIn("amin@ttpu.uz"), onSignIn = { _, _ -> })
}
