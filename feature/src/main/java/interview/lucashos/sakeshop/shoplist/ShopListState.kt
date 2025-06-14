package interview.lucashos.sakeshop.shoplist

import interview.lucashos.sakeshop.domain.model.SakeShop

data class ShopListState(
    val shops: List<SakeShop> = listOf()
)

sealed class ShopListEvent {
    data object Init : ShopListEvent()

    data class ToDetails(val shop: SakeShop) : ShopListEvent()
}