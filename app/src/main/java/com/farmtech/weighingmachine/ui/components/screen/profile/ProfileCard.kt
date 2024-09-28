package com.farmtech.weighingmachine.ui.components.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.material.OutlinedButton
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun ProfileCard(onLogout: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(ColorTheme.primary200).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Profile Picture", style = TypographyTheme.displayMedium)

            AsyncImage(
                model = "https://static-content-live.caricarz.com/media_library/car/624168/13242788/conversions/624168_1724142632-thumb.webp",
                placeholder = painterResource(id = R.drawable.profile_placeholder),
                error = painterResource(id = R.drawable.profile_placeholder),
                contentDescription = "The delasign logo",
                modifier = Modifier.size(140.dp)
            )

            OutlinedButton(
                onClick = { onLogout() },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text("Logout")
            }
        }
    }
}