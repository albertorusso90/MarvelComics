package com.albertorusso.marvelcomics.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.albertorusso.marvelcomics.R

@Composable
fun CharacterItem(
    name: String,
    image: String,
    imageSize: Int
) {
    Box {
        // Image with fixed max width
        Image(
            painter = rememberImagePainter(
                data = image,
                builder = {
                    error(R.drawable.error_placeholder) // Replace with your error placeholder drawable
                    placeholder(R.drawable.placeholder) // Replace with your placeholder drawable
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .width(imageSize.dp) // Width of each item
                .height(imageSize.dp) // Height of each item
                .clip(shape = RoundedCornerShape(8.dp)), // Apply rounded corners if desired
            contentScale = ContentScale.Fit
        )
        
        // Text positioned at the bottom of the image
        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier
                .width(imageSize.dp)
                .align(Alignment.TopCenter) // Positioning the text at the bottom
                .padding(8.dp) // Adjust padding as needed
                .background(color = Color.Black.copy(alpha = 0.5f)) // Semi-transparent background
        )
    }
}
