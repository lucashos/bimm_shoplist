package interview.lucashos.sakeshop.shoplist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import interview.lucashos.sakeshop.core.di.IoDispatcher
import interview.lucashos.sakeshop.domain.model.SakeShop
import interview.lucashos.sakeshop.domain.usecase.ListSakeShopsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val listSakeShopsUseCase: ListSakeShopsUseCase
) : ViewModel() {
    var state by mutableStateOf(ShopListState())
        private set

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    fun onEvent(event: ShopListEvent) {
        when (event) {
            is ShopListEvent.Init -> viewModelScope.launch(dispatcher) {
                val shops = listSakeShopsUseCase()
                state = state.copy(shops = shops)
            }

            is ShopListEvent.ToDetails -> viewModelScope.launch(dispatcher) {
                _events.emit(UiEvent.ToDetails(shop = event.shop))
            }
        }
    }

    sealed class UiEvent {
        data class ToDetails(val shop: SakeShop) : UiEvent()
    }
}