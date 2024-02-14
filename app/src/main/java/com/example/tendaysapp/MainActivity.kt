package com.example.tendaysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tendaysapp.model.Maravilla
import com.example.tendaysapp.model.maravillas
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

@Composable
fun MaravillaItem(
    maravilla: Maravilla,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer
    )
    Card(modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp)) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ).background(color = color),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 72.dp)
                    .padding(start = 14.dp, top = 14.dp, end = 14.dp)
            ) {
                MaravillaInformation(maravilla.nameRes, maravilla.dayRes, Modifier.weight(1f))
                MaravillaIcon(maravillaIcon = maravilla.imageRes, Modifier.padding(start = 10.dp))
            }
            MaravillaItemButton(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )
            if (expanded) {
                Column(modifier = modifier) {
                    Text(
                        text = stringResource(maravilla.descriptionRes),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
private fun MaravillaItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun MaravillaInformation(
    @StringRes maravillaName: Int,
    @StringRes maravillaDay: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(maravillaDay),
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = stringResource(maravillaName),
            style = MaterialTheme.typography.displayMedium
        )
    }
}


@Composable
fun MaravillaIcon(
    @DrawableRes maravillaIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(100.dp)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(maravillaIcon),
        contentDescription = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaravillaTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(85.dp)
                        .padding(5.dp),
                    painter = painterResource(R.drawable.scafol),
                    contentDescription = null
                )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.bodyLarge
            )
                Image(
                    modifier = Modifier
                        .size(85.dp)
                        .padding(5.dp),
                    painter = painterResource(R.drawable.scafol),
                    contentDescription = null
                )
          }
        },
        modifier = modifier
    )
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