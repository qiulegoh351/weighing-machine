package com.farmtech.weighingmachine.ui.screen.auth.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.farmtech.weighingmachine.ui.components.Scaffold
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.navigation.NavController

@Composable
fun SignUp(appNavController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Text("SignUp Screen")
        }
    }
}