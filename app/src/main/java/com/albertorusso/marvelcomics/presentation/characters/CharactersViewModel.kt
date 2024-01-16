package com.albertorusso.marvelcomics.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertorusso.marvelcomics.domain.models.Result
import com.albertorusso.marvelcomics.presentation.models.SimpleMarvelCharacter
import com.albertorusso.marvelcomics.domain.usecase.GetCharactersUseCase
import com.albertorusso.marvelcomics.presentation.mappers.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    
    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    private val characters = MutableLiveData<List<SimpleMarvelCharacter>>()
    
    fun viewState(): LiveData<ViewState> = viewState
    fun characters(): LiveData<List<SimpleMarvelCharacter>> = characters
    
    fun fetchCharacters(name: String) {
        viewModelScope.launch {
            
            try {
                if(name.isNotEmpty()) {
                    viewState.value = ViewState.Loading
                    when (val result = getCharactersUseCase(name)) {
                        is Result.Success -> {
                            viewState.value = ViewState.Loaded(Mapper().map(result.data))
                        }
                        is Result.Error -> {
                            viewState.value = ViewState.Error(result.message)
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
        data class Loaded(val characters: List<SimpleMarvelCharacter>) : ViewState()
        data class Error(val errorMessage: String?) : ViewState()
    }
    
}
