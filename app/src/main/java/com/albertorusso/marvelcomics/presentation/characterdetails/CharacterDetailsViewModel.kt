package com.albertorusso.marvelcomics.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.marvelcomics.domain.model.MarvelCharacter
import com.albertorusso.marvelcomics.domain.usecase.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {
    
    private val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    private val character = MutableLiveData<MarvelCharacter?>()
    
    fun state(): LiveData<LoadingState> = loadingState
    fun character(): LiveData<MarvelCharacter?> = character
    
    fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            try {
                loadingState.value = LoadingState.IN_PROGRESS
                
                if(id > 0) {
                    val characterList = getCharacterDetailsUseCase(id)
                    character.postValue(characterList)
                }
                
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
