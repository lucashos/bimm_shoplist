package interview.lucashos.sakeshop.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SakeShop(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: Coordinates,
    val googleMapsLink: String,
    val website: String
): Parcelable
