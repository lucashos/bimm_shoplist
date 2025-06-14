package interview.lucashos.sakeshop.shopdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import interview.lucashos.sakeshop.core.di.IoDispatcher
import interview.lucashos.sakeshop.domain.model.Coordinates
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopDetailsViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    var state by mutableStateOf(ShopDetailsState())
        private set

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    fun onEvent(event: ShopDetailsEvent) {
        when (event) {
            is ShopDetailsEvent.Init -> state = state.copy(
                shop = event.shop
            )

            ShopDetailsEvent.Back -> viewModelScope.launch(dispatcher) {
                _events.emit(UiEvent.Back)
            }

            is ShopDetailsEvent.OpenAddress -> viewModelScope.launch(dispatcher) {
                _events.emit(UiEvent.OpenMaps(
                    address = event.address,
                    coordinates = event.coordinates
                ))
            }

            is ShopDetailsEvent.OpenWebsite -> viewModelScope.launch(dispatcher) {
                _events.emit(UiEvent.OpenWebsite(website = event.website))
            }
        }
    }

    sealed class UiEvent {

        data class OpenMaps(val address: String, val coordinates: Coordinates) : UiEvent()

        data class OpenWebsite(val website: String) : UiEvent()

        data object Back : UiEvent()
    }
}