package com.farmtech.weighingmachine.module

import com.farmtech.weighingmachine.ui.screen.auth.signin.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SignInViewModel() } // Provide the SignInViewModel
}