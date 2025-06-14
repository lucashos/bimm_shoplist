package interview.lucashos.sakeshop.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SakeShopTO(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "picture") val picture: String?,
    @Json(name = "rating") val rating: Double,
    @Json(name = "address") val address: String,
    @Json(name = "coordinates") val coordinates: CoordinatesTO,
    @Json(name = "google_maps_link") val googleMapsLink: String,
    @Json(name = "website") val website: String
)
