package com.example.adriann.state1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adriann.state1.ui.theme.StateDemoTheme
import com.example.adriann.state1.viewmodel.HelloViewModel
import com.example.adriann.state1.viewmodel.HelloViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: HelloViewModel by viewModels {
        HelloViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HelloScreen(viewModel = viewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    HelloScreen(viewModel = HelloViewModel())
}

@Composable
fun HelloScreen(viewModel: HelloViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val name by viewModel.name.observeAsState("")
        HelloContent(name = name, onNameChange = { viewModel.onNameChange(it) })

    }
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h4
            )
        }
        OutlinedTextField(value = name,
            onValueChange = onNameChange,
            label = {
                Text("Name")
            }
        )
    }
}