package com.norm.myfocusmanagement

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.norm.myfocusmanagement.ui.theme.MyFocusManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var fullName by remember {
                mutableStateOf("")
            }
            var email by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            var isVisiblePassword by remember {
                mutableStateOf(false)
            }

            val focusRequester = remember {
                FocusRequester()
            }
            val focusManager = LocalFocusManager.current

            MyFocusManagementTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            text = "User Info"
                        )
                        TextField(
                            value = fullName,
                            onValueChange = {
                                fullName = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            label = {
                                Text(
                                    text = "Full Name"
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                        )
                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Email"
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                        )
                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = {
                                Text(
                                    text = "Password"
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        isVisiblePassword = !isVisiblePassword
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (isVisiblePassword) Icons.Outlined.Lock else Icons.Default.Lock,
                                        contentDescription = null,
                                    )
                                }
                            },
                            singleLine = true,
                            visualTransformation = if (isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    Toast.makeText(
                                        applicationContext,
                                        "From submit by keyboard",
                                        Toast.LENGTH_LONG,
                                    ).show()
                                }
                            )
                        )
                        Button(
                            onClick = {
                                focusRequester.requestFocus()
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                        ) {
                            Text(
                                text = "Start filing out from",
                            )
                        }
                        Button(
                            onClick = {
                                Toast.makeText(
                                    applicationContext,
                                    "The from was submit by button click!",
                                    Toast.LENGTH_LONG,
                                ).show()
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                        ) {
                            Text(
                                text = "Submit",
                            )
                        }
                        Button(
                            onClick = {
                                focusManager.moveFocus(FocusDirection.Next)
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                        ) {
                            Text(
                                text = "Next from",
                            )
                        }
                    }
                }
            }
        }
    }
}