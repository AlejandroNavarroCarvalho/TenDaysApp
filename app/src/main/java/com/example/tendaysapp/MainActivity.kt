package com.example.tendaysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tendaysapp.DataSource.MaravillaDataSource
import com.example.tendaysapp.ui.theme.TenDaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TenDaysAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TenDaysApp()
                }
            }
        }
    }
}

val maravillas = MaravillaDataSource().getMaravillas()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TenDaysApp() {
    Scaffold(
        topBar = {
            MaravillaTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(maravillas) {
                MaravillaItem(
                    maravilla = it,
                    modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaravillasPreview() {
    TenDaysAppTheme {
        TenDaysApp()
    }
}

@Preview(showBackground = true)
@Composable
fun MaravillasDarkPreview() {
    TenDaysAppTheme (darkTheme = true) {
        TenDaysApp()
    }
}