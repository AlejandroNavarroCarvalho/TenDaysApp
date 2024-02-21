package com.example.tendaysapp

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tendaysapp.model.Maravilla

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
    val animValue = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        animValue.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 750, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    Card(modifier = modifier
        .border(
            width = 1.8.dp,
            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = animValue.value),
            shape = MaterialTheme.shapes.medium
        ),
        elevation = CardDefaults.cardElevation(2.dp)) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 72.dp)
                    .padding(start = 14.dp, top = 14.dp, end = 14.dp)
            ) {
                MaravillaInformation(maravilla.nameRes, maravilla.dayRes, Modifier.weight(1f))
                MaravillaIcon(
                    maravillaIcon = maravilla.imageRes,
                    expanded,
                    modifier = Modifier.padding(start = 10.dp)
                )
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
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val size = animateDpAsState(if (expanded) 150.dp else 100.dp)
    Image(
        modifier = modifier
            .size(size.value)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(maravillaIcon),
        contentDescription = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaravillaTopAppBar(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        modifier = modifier.clickable {
            val calendarIntent = Intent(Intent.ACTION_VIEW).apply {
                setData(Uri.parse("content://com.android.calendar/time"))
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(calendarIntent)
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.icono_scafold), contentDescription = null, modifier.size(75.dp))
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}