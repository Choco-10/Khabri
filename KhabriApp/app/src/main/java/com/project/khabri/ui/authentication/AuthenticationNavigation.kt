package com.project.pattagobhi.ui.authentication

import kotlinx.serialization.Serializable

interface AuthenticationNavigation {
    @Serializable
    object LoginScreen: AuthenticationNavigation

    @Serializable
    object CreateAccountScreen: AuthenticationNavigation

    @Serializable
    data class EmailVerificationScreen(val email: String, val password: String): AuthenticationNavigation

    @Serializable
    object ForgotPasswordScreen: AuthenticationNavigation

    @Serializable
    object LoginSuccessScreen: AuthenticationNavigation

    @Serializable
    object CreateAccountPasswordScreen: AuthenticationNavigation
}