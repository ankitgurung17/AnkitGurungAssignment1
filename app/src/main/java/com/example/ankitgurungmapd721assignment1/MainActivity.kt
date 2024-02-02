package com.example.ankitgurungmapd721assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.ankitgurungmapd721assignment1.data.UserStore
import com.example.ankitgurungmapd721assignment1.ui.theme.AnkitGurungMAPD721Assignment1Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnkitGurungMAPD721Assignment1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Main() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val userNameValue = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val studentIDValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val store = UserStore(context)

    Column(
        modifier = Modifier.clickable { keyboardController?.hide() },
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Assignment 1", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = userNameValue.value,
            onValueChange = { userNameValue.value = it },
            label = {Text("Username")},
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = emailValue.value,
            onValueChange = { emailValue.value = it },
            label = { Text("Email") },
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = studentIDValue.value,
            onValueChange = { studentIDValue.value = it },
            label = { Text("ID") },
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val loadedUserName = store.getUsername.first()
                        val loadedEmail = store.getEmail.first()
                        val loadedStudentId = store.getStudentID.first()

                        userNameValue.value = TextFieldValue(loadedUserName)
                        emailValue.value = TextFieldValue(loadedEmail)
                        studentIDValue.value = TextFieldValue(loadedStudentId)
                    }
                }
            ) {
                Text(text = "Load")
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.saveUsername(userNameValue.value.text)
                        store.saveEmail(emailValue.value.text)
                        store.saveStudentID(studentIDValue.value.text)
                    }
                }
            ) {
                Text(text = "Save")
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.clearData()
                    }
                }
            ) {
                Text(text = "Clear")
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        Divider()
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Ankit Singh Gurung", fontWeight = FontWeight.Bold)
        Text(text = "301369705", fontWeight = FontWeight.Bold)

    }

}
