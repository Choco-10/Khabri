package com.project.khabri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.project.khabri.domain.repositories.UserRepo
import com.project.khabri.ui.authentication.login.LoginViewModel
import com.project.khabri.ui.theme.KhabriTheme
import com.project.pattagobhi.ui.authentication.AuthenticationNavigation
import com.project.pattagobhi.ui.authentication.createAccount.CreateAccountEmailScreen
import com.project.pattagobhi.ui.authentication.createAccount.CreateAccountPasswordScreen
import com.project.pattagobhi.ui.authentication.createAccount.CreateAccountViewModel
import com.project.pattagobhi.ui.authentication.emailVerification.EmailVerificationViewModel
import com.project.pattagobhi.ui.authentication.emailVerification.ForgotPassword
import com.project.pattagobhi.ui.authentication.emailVerification.LogInSuccess
import com.project.pattagobhi.ui.authentication.emailVerification.VerifyEmail
import com.project.pattagobhi.ui.authentication.login.LogInScreen
import com.project.khabri.ui.home.MainScreen
import com.project.pattagobhi.ui.navigation.PrimaryNavigation
import com.project.khabri.ui.authentication.onboarding.OnBoardingScreen
import com.project.khabri.ui.feed.FeedViewModel
import com.project.khabri.ui.journalist.NewsWritingViewModel
import kotlinx.coroutines.launch


import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val loginViewModel by inject<LoginViewModel>()
    val emailVerificationViewModel by inject<EmailVerificationViewModel>()
    val createAccountViewModel by inject<CreateAccountViewModel>()
    val feedViewModel by inject<FeedViewModel>()
    val newsWritingViewModel by inject<NewsWritingViewModel>()
    val userRepo by inject<UserRepo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            KhabriTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                NavHost(
                    navController = navController,
                    startDestination = if (FirebaseAuth.getInstance().currentUser != null)
                        PrimaryNavigation.MainScreen else PrimaryNavigation.OnBoardingScreen
                ) {
                    composable<AuthenticationNavigation.LoginScreen> {
                        LogInScreen(viewModel = loginViewModel, navController = navController)
                    }
                    composable<AuthenticationNavigation.CreateAccountPasswordScreen> {
                        CreateAccountPasswordScreen(
                            viewModel = createAccountViewModel,
                            navController = navController
                        )
                    }
                    composable<AuthenticationNavigation.CreateAccountScreen> {
                        CreateAccountEmailScreen(
                            viewModel = createAccountViewModel,
                            navController = navController
                        )
                    }
                    composable<AuthenticationNavigation.LoginSuccessScreen> {
                        LogInSuccess(navController = navController)
                    }
                    composable<AuthenticationNavigation.ForgotPasswordScreen> {
                        ForgotPassword(navController = navController)
                    }
                    composable<AuthenticationNavigation.EmailVerificationScreen> {
                        val arg = it.toRoute<AuthenticationNavigation.EmailVerificationScreen>()
                        VerifyEmail(
                            viewModel = emailVerificationViewModel,
                            navController = navController,
                            email = arg.email,
                            password = arg.password
                        )
                    }
                    composable<PrimaryNavigation.LoadingScreen> {

                    }
                    composable<PrimaryNavigation.MainScreen> {
                        val scope = rememberCoroutineScope()
                        scope.launch {
                            userRepo.pushUserData()
                        }
                        MainScreen(feedViewModel,newsWritingViewModel)
                    }

                    composable<PrimaryNavigation.OnBoardingScreen> {
                        OnBoardingScreen(navController = navController)
                    }
                }

            }
        }
    }
}