package interview.lucashos.sakeshop.shopdetails

data class ShopDetailsState(
    val screenName: String = "ShopDetails Screen"
)

sealed class ShopDetailsEvent {
    data object ShowToast : ShopDetailsEvent()
}