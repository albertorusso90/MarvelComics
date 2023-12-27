package com.albertorusso.marvelcomics.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
){
    if (isDisplayed) {
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
