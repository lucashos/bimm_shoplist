package interview.lucashos.sakeshop.domain.model

data class SakeShop(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: Coordinates,
    val googleMapsLink: String,
    val website: String
)
