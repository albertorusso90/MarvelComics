package com.albertorusso.marvelcomics.presentation.characters

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.albertorusso.marvelcomics.presentation.ui.theme.MarvelComicsTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.albertorusso.marvelcomics.presentation.components.CircularIndeterminateProgressBar
import com.albertorusso.marvelcomics.presentation.components.ErrorText
import com.albertorusso.marvelcomics.R
import com.albertorusso.marvelcomics.domain.model.SimpleMarvelCharacter
import com.albertorusso.marvelcomics.presentation.characterdetails.CharacterDetailsActivity

@AndroidEntryPoint
class CharactersActivity : ComponentActivity() {
    
    private val viewModel: CharactersViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MarvelComicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CharactersScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(viewModel: CharactersViewModel) {
    val textToShow = stringResource(id = R.string.error_loading)
    
    val loadingState by viewModel.state().observeAsState()
    val characters by viewModel.characters().observeAsState(initial = emptyList())
    var searchText by remember { mutableStateOf("") }
    
    // Access the Composable's Context
    val context = LocalContext.current
    
    // Handler for delaying text input handling
    val handler = remember { Handler(context.mainLooper) }
    
    // Function to fetch characters after a delay
    fun fetchCharactersWithDelay(newText: String) {
        handler.removeCallbacksAndMessages(null) // Remove existing callbacks
        handler.postDelayed({
            viewModel.fetchCharacters(newText)
        }, 300) // Set the delay time (e.g., 300 milliseconds)
    }
    
    // Display search bar and list of items
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        
        TextField(
            value = searchText,
            onValueChange = { newValue ->
                searchText = newValue
                fetchCharactersWithDelay(newValue)
            },
            label = { Text(text = "Search for marvel characters")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if(loadingState == CharactersViewModel.LoadingState.LOADED) {
                LazyVerticalGrid(
                    GridCells.Fixed(2), // 2 items per row
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(characters) { character ->
                        MarvelCharacterItem(character = character) { characterId ->
                            val intent = Intent(context, CharacterDetailsActivity::class.java)
                            intent.putExtra(CharacterDetailsActivity.CHARACTER_ID_EXTRA, characterId)
                            context.startActivity(intent)
                        }
                    }
                }
            }
    
            ErrorText(isDisplayed = loadingState == CharactersViewModel.LoadingState.ERROR, textToShow = textToShow)
            CircularIndeterminateProgressBar(isDisplayed = loadingState == CharactersViewModel.LoadingState.IN_PROGRESS)
        }
    }
}

@Composable
fun MarvelCharacterItem(character: SimpleMarvelCharacter, onItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onItemClick(character.id) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(
                data = character.photo,
                builder = {
                    error(R.drawable.error_placeholder) // Replace with your error placeholder drawable
                    placeholder(R.drawable.placeholder) // Replace with your placeholder drawable
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = character.name,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

