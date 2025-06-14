package interview.lucashos.sakeshop.shopdetails

import interview.lucashos.sakeshop.domain.model.Coordinates
import interview.lucashos.sakeshop.domain.model.SakeShop

data class ShopDetailsState(
    val shop: SakeShop? = null
)

sealed class ShopDetailsEvent {
    data class Init(val shop: SakeShop) : ShopDetailsEvent()

    data object Back : ShopDetailsEvent()

    data class  OpenAddress(val address: String, val coordinates: Coordinates): ShopDetailsEvent()

    data class OpenWebsite(val website: String): ShopDetailsEvent()
}