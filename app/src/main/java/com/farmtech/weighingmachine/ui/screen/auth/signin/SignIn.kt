package com.farmtech.weighingmachine.ui.screen.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.input.PasswordInput
import com.farmtech.weighingmachine.ui.components.input.SelectInput
import com.farmtech.weighingmachine.ui.components.input.TextInput
import com.farmtech.weighingmachine.ui.components.material.Button
import com.farmtech.weighingmachine.ui.components.material.KeyboardAvoidingView
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.material.TouchableOpacity
import com.farmtech.weighingmachine.ui.components.utils.logMessage
import com.farmtech.weighingmachine.ui.navigation.NavController
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignIn(
    appNavController: NavController,
    modifier: Modifier = Modifier,
    vm: SignInViewModel = koinViewModel(),
) {
    val viewState by vm.state.collectAsState()
    val navEvent by vm.navigationEvents.collectAsState(initial = null)

    navEvent.let { event ->
        (event as? NavigationEvent.Success)?.let {
            logMessage("success login")
        }
    }

    SignInContent(
        viewState,
        setCompany = { vm.setCompany(it) },
        setEmail = { vm.setEmail(it) },
        setPassword = { vm.setPassword(it) },
        onSubmit = { vm.onSubmit() },
        modifier
    )
}

data class Company(
    val company_name: String,
    val company_id: Int,
    val company_status: String
)

data class Fleet(
    val fleet_id: Int,
    val fleet_name: String,
    val company: Company
)

val fleetOptions = listOf(
    Fleet(27, "Fleet A", Company("Company Durion King", 9, "active")),
    Fleet(28, "Fleet B", Company("Company Musang King", 10, "inactive")),
    Fleet(28, "Fleet C", Company("Company D11", 9, "active")),
    Fleet(29, "Fleet D", Company("Company D101", 10, "inactive"))
)

@Composable
fun SignInContent(
    viewState: SignInViewState,
    setCompany: (Int?) -> Unit,
    setEmail: (String) -> Unit,
    setPassword: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White,
    ) {
        KeyboardAvoidingView(
            modifier = Modifier.padding(horizontal = 48.dp, vertical = 60.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.farmtech_logo),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(width = 245.dp, height = 50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier.fillMaxWidth(.5f),
                verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Sign In", style = TypographyTheme.displayMedium)
                    Text(
                        "Welcome back! Please enter your details.",
                        style = TypographyTheme.titleMedium.copy(
                            fontWeight = FontWeight.Normal,

                            ),
                        color = ColorTheme.textSecondary
                    )
                }

                SelectInput(
                    label = "Company",
                    placeholder = "Select Company",
                    options = fleetOptions,
                    value = viewState.companyId,
                    onValueChange = { setCompany(it) },
                    labelKey = { it.company.company_name },
                    valueKey = { it.company.company_id },
                    loading = false,
                    deSelect = true
                )

                TextInput(
                    value = viewState.email,
                    onValueChange = { setEmail(it) },
                    label = "Email Address",
                    placeholder = "Enter Emaill Address",
                )
                PasswordInput(
                    value = viewState.password,
                    onValueChange = { setPassword(it) },
                    label = "Password",
                    placeholder = "Enter Password"
                )

                Button(
                    loading = viewState.loading,
                    onClick = {
                        onSubmit()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Login", color = Color.White)
                }

                TouchableOpacity(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "Forgot Password?",
                        color = ColorTheme.primaryButton,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}