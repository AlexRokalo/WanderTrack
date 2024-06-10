package com.ar.wandertrack.presentation.ui.authorization.login

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ar.wandertrack.R
import com.ar.wandertrack.presentation.theme.WanderTrackTheme

@Composable
fun LoginScreen(
    contentPadding: PaddingValues = PaddingValues(),
) {
    val loginViewModel = viewModel<LoginViewModel>()

    val configuration = LocalConfiguration.current
    val fraction = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        1f
    } else {
        0.7f
    }

    val emailError = loginViewModel.emailError.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = contentPadding,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            Text(
                text = "WanderTrack",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 96.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillParentMaxHeight(fraction = fraction),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Create an account",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Enter your email to sign up for this app"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Email(viewModel = loginViewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        enabled = emailError.value.not(),
                        onClick = {
                            loginViewModel.login()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(text = "Sign up with email")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OrContinueWith()
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedButtonWithIcon(
                        imageResId = R.drawable.google_logo,
                        contentDescription = null,
                        text = "Google",
                        onClick = {
                            loginViewModel.signInWithCredential()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TermsAndPrivacyText(onTermsClick = {}, onPrivacyClick = {})
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun OrContinueWith() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            color = Color(0xFF999999),
            text = "or continue with",
            modifier = Modifier.padding(horizontal = 8.dp),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

@Composable
fun TermsAndPrivacyText(
    onTermsClick: () -> Unit, onPrivacyClick: () -> Unit
) {
    val annotatedString = remember {
        buildAnnotatedString {
            append("By clicking continue, you agree to our ")

            // Adding Terms of Service text
            pushStringAnnotation(
                tag = "TERMS",
                annotation = "terms"
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Terms of Service")
            }
            pop()

            append(" and ")

            // Adding Privacy Policy text
            pushStringAnnotation(
                tag = "PRIVACY",
                annotation = "privacy"
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Privacy Policy")
            }
            pop()
        }
    }

    ClickableText(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp),
        text = annotatedString,
        style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp),
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(tag = "TERMS", start = offset, end = offset)
                .firstOrNull()?.let {
                    onTermsClick()
                }
            annotatedString
                .getStringAnnotations(tag = "PRIVACY", start = offset, end = offset)
                .firstOrNull()?.let {
                    onPrivacyClick()
                }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(viewModel: LoginViewModel = viewModel()) {
    val email by viewModel.email.collectAsState()
    val emailError by viewModel.emailError.collectAsState()

    OutlinedTextField(
        value = email,
        onValueChange = { newEmail -> viewModel.onEmailChanged(newEmail) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFE0E0E0),
            unfocusedBorderColor = Color(0xFFEEEEEE),
            errorBorderColor = Color.Red
        ),
        isError = emailError,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = MaterialTheme.shapes.small)
            .padding(start = 24.dp, end = 24.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        placeholder = { Text(text = "email@domain.com") }
    )

    if (emailError) {
        TextFieldError(textError = "Please enter a valid email")
    }
}

@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun OutlinedButtonWithIcon(
    imageResId: Int,
    contentDescription: String?,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0xFFEEEEEE),
            contentColor = Color.Black,
        ),
        border = BorderStroke(width = 0.dp, color = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}


@Preview
@Composable
fun SignInSignUpScreenPreview() {
    WanderTrackTheme {
        Surface {
            LoginScreen()
        }
    }
}