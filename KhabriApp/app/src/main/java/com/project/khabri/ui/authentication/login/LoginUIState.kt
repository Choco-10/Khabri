package com.project.khabri.ui.authentication.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordValid: Boolean = true,
    val passwordError: String = "",
    val isEmailValid: Boolean = true,
    val emailError: String = ""
)
