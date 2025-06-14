package interview.lucashos.sakeshop.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinatesTO(
    val latitude: Double,
    val longitude: Double
)
