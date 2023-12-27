package com.albertorusso.marvelcomics.presentation.characterdetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
    
        // Retrieve the character ID from intent extras
        
        viewModel.state().observe(this) {
            //update status
        }
        
        viewModel.character().observe(this) {
            // update details
        }
        
        viewModel.fetchCharacter(intent.getIntExtra(CHARACTER_ID_EXTRA, -1))
    
        setContent {
            MarvelComicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                }
            }
        }
    }


}
