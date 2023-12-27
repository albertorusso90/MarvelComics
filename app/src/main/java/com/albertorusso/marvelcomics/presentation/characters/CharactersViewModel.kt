package com.albertorusso.marvelcomics.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.marvelcomics.domain.model.SimpleMarvelCharacter
import com.albertorusso.marvelcomics.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    
    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    private val characters = MutableLiveData<List<SimpleMarvelCharacter>>()
    
    fun state(): LiveData<LoadingState> = loadingState
    fun characters(): LiveData<List<SimpleMarvelCharacter>> = characters
    
    fun fetchCharacters() {
        loadingState.value = LoadingState.IN_PROGRESS
        viewModelScope.launch {
            try {
                val characterList = getCharactersUseCase("Hulk")
                characters.postValue(characterList)
                loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                loadingState.value = LoadingState.ERROR
            }
        }
    }
    
    enum class LoadingState {
        IN_PROGRESS, LOADED, ERROR
    }
}
