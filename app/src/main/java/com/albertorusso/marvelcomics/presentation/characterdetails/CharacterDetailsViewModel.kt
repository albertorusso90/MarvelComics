package com.albertorusso.marvelcomics.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.marvelcomics.domain.models.Result
import com.albertorusso.marvelcomics.domain.models.MarvelCharacter
import com.albertorusso.marvelcomics.domain.usecase.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {
    
    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    
    fun viewState(): LiveData<ViewState> = viewState
    
    fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            try {
                viewState.value = ViewState.Loading
                
                if(id > 0) {
                    when (val characterResult = getCharacterDetailsUseCase(id)) {
                        is Result.Success -> {
                            viewState.value = ViewState.Loaded(characterResult.data)
                        }
                        is Result.Error -> {
                            viewState.value = ViewState.Error(characterResult.message)
                        }
                    }
                }
            } catch (e: Exception) {
                viewState.value = ViewState.Error(e.message)
            }
        }
    }
    
    sealed class ViewState {
        object Loading : ViewState()
        data class Loaded(val character: MarvelCharacter) : ViewState()
        data class Error(val errorMessage: String?) : ViewState()
    }
}
