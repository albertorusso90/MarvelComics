package com.albertorusso.marvelcomics.presentation.characterdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.albertorusso.marvelcomics.R
import com.albertorusso.marvelcomics.domain.models.ImageItem
import com.albertorusso.marvelcomics.presentation.characterdetails.CharacterDetailsViewModel.ViewState
import com.albertorusso.marvelcomics.presentation.components.CharacterItem
import com.albertorusso.marvelcomics.presentation.components.CircularIndeterminateProgressBar
import com.albertorusso.marvelcomics.presentation.components.ErrorText
import com.albertorusso.marvelcomics.presentation.ui.theme.MarvelComicsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailsActivity : ComponentActivity() {
    
    companion object {
        const val CHARACTER_ID_EXTRA = "CHARACTER_ID"
    }
    
    private val viewModel: CharacterDetailsViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel.fetchCharacter(intent.getIntExtra(CHARACTER_ID_EXTRA, -1))
        
        setContent {
            MarvelComicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CharacterDetailScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailsViewModel) {
    val viewState by viewModel.viewState().observeAsState(initial = ViewState.Loading)
    
    // Display search bar and list of items
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (viewState) {
                is ViewState.Loading -> {
                    CircularIndeterminateProgressBar(isDisplayed = true)
                }
                is ViewState.Error -> {
                    val errorViewState = viewState as ViewState.Error
                    ErrorText(isDisplayed = true, textToShow  = errorViewState.errorMessage ?: stringResource(id = R.string.error_loading))
                }
                is ViewState.Loaded -> {
                    val character = (viewState as ViewState.Loaded).character
                    
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        Box {
                            // Image with fixed max width
                            Image(
                                painter = rememberImagePainter(
                                    data = character.image,
                                    builder = {
                                        error(R.drawable.error_placeholder) // Replace with your error placeholder drawable
                                        placeholder(R.drawable.placeholder) // Replace with your placeholder drawable
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp) // Setting a fixed height for the image
                                    .clip(shape = RoundedCornerShape(8.dp)), // Apply rounded corners if desired
                                contentScale = ContentScale.Crop
                            )
            
                            // Text positioned at the bottom of the image
                            Text(
                                text = character.name,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.TopCenter) // Positioning the text at the bottom
                                    .padding(16.dp) // Adjust padding as needed
                                    .background(color = Color.Black.copy(alpha = 0.5f)) // Semi-transparent background
                            )
                        }
        
                        HorizontalList(stringResource(id = R.string.comics), items = character.comics)
                        HorizontalList(stringResource(id = R.string.events), items = character.events)
                        HorizontalList(stringResource(id = R.string.series), items = character.series)
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalList(title: String, items: List<ImageItem>) {
    Text(text = title,
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 20.dp, bottom = 8.dp))
    
    if(items.isEmpty()) {
        Text(text = stringResource(id = R.string.empty_data),
            color = Color.Black,
            fontSize = 12.sp
        )
    } else {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) {
                CharacterItem(name = it.name, image = it.image, imageSize = 150)
            }
        }
    }
    
}
