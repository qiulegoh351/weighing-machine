package com.farmtech.weighingmachine.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.AuthRepository
import com.farmtech.weighingmachine.ui.components.Scaffold
import com.farmtech.weighingmachine.ui.components.screen.profile.PersonalInfo
import com.farmtech.weighingmachine.ui.components.screen.profile.ProfileCard
import com.farmtech.weighingmachine.ui.container.profile.SecurityInfo
import com.farmtech.weighingmachine.ui.navigation.NavController

@Composable
fun Profile(appNavController: NavController, modifier: Modifier = Modifier) {
    // ========================= VIEW
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.padding(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // =============== Profile Image & Change Image, Logout Action
                ProfileCard(
                    onLogout = { AuthRepository.signOut() },
                    modifier = modifier.weight(.4f)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PersonalInfo()

                    SecurityInfo()
                }
            }
        }
    }

}