package uz.ttpu.composearchlab.ui.taste

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TastePickerViewModel : ViewModel() {

    private val _state = MutableStateFlow(TastePickerState(artists = seedArtists))
    val state: StateFlow<TastePickerState> = _state.asStateFlow()

    fun onGenreClick(genre: String) {
        _state.update {
            if (it.selectedGenre == genre) it.copy(selectedGenre = null)
            else it.copy(selectedGenre = genre)
        }
    }

    fun onArtistLikeToggled(id: Int) {
        _state.update {
            if (id in it.likedIds) it.copy(likedIds = it.likedIds - id)
            else it.copy(likedIds = it.likedIds + id)
        }
    }
}
