package com.project.pattagobhi.ui.authentication.createAccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.khabri.R
import com.project.pattagobhi.ui.components.AppButton
import com.project.khabri.ui.components.AppTextField
import com.project.pattagobhi.ui.components.HeadingOfCreateAccount
import com.project.pattagobhi.ui.components.Wait
import com.project.pattagobhi.ui.authentication.AuthenticationNavigation
import com.project.pattagobhi.util.confirmPasswordCheck
import com.project.pattagobhi.util.nameCheck
import com.project.pattagobhi.util.passwordCheck


@Composable
fun CreateAccountPasswordScreen(viewModel: CreateAccountViewModel, navController: NavController) {
    val uiState by viewModel.uiState
    if (!uiState.isNameValid)
        nameCheck(uiState.name, viewModel::onNameErrorStateChange)
    if (!uiState.isPasswordValid)
        passwordCheck(uiState.password, viewModel::onPasswordErrorStateChange)
    if (!uiState.isConfirmPasswordValid)
        confirmPasswordCheck(
            uiState.password,
            uiState.confirmPassword,
            viewModel::onConfirmPasswordErrorStateChange
        )
    val passwordIcon = Icons.Default.Password
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .fillMaxHeight(0.77f)
                    .clip(shape = RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                if (uiState.isLoading)
                    Wait()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        HeadingOfCreateAccount(
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                    }
                    item {
                        AppTextField(
                            modifier = Modifier,
                            value = uiState.name,
                            onValueChange = viewModel::onNameChange,
                            outerText = "Name",
                            placeholderText = "Enter your name",
                            icon = Icons.Default.Person,
                            isError = !uiState.isNameValid,
                            errorText = uiState.nameError
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(28.dp))
                        AppTextField(
                            modifier = Modifier,
                            value = uiState.password,
                            onValueChange = viewModel::onPasswordChange,
                            outerText = "Password",
                            placeholderText = "Enter your password",
                            icon = passwordIcon,
                            isPassword = true,
                            isError = !uiState.isPasswordValid,
                            errorText = uiState.passwordError,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(28.dp))
                        AppTextField(
                            modifier = Modifier,
                            isPassword = true,
                            value = uiState.confirmPassword,
                            onValueChange = viewModel::onConfirmPasswordChange,
                            outerText = "Confirm Password",
                            placeholderText = "Confirm your password",
                            icon = passwordIcon,
                            isError = !uiState.isConfirmPasswordValid,
                            errorText = uiState.confirmPasswordError,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(48.dp))
                        AppButton(
                            modifier = Modifier.width(314.dp),
                            isEnabled = !uiState.isLoading
                        ) {
                            if (nameCheck(
                                    uiState.name,
                                    viewModel::onNameErrorStateChange
                                )
                            ) return@AppButton
                            if (passwordCheck(
                                    uiState.password,
                                    viewModel::onPasswordErrorStateChange
                                )
                            ) return@AppButton
                            if (confirmPasswordCheck(
                                    uiState.password,
                                    uiState.confirmPassword,
                                    viewModel::onConfirmPasswordErrorStateChange
                                )
                            ) return@AppButton
                            viewModel.createAccount(snackbarHostState) {
                                navController.navigate(
                                    AuthenticationNavigation.EmailVerificationScreen(
                                        email = uiState.email,
                                        password = uiState.password
                                    )
                                )
                            }

                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.07f))
                Image(
                    painter = painterResource(id = R.drawable.logo_name),
                    contentDescription = "logo + name",
                    colorFilter = ColorFilter.tint(Color(0xffeef6f8)),
                    modifier = Modifier
                        .requiredWidth(width = 99.dp)
                        .requiredHeight(height = 84.dp)
                )
            }
        }
    }
}