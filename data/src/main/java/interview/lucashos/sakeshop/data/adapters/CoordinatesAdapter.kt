package interview.lucashos.sakeshop.data.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import interview.lucashos.sakeshop.data.model.CoordinatesTO
import interview.lucashos.sakeshop.domain.model.Coordinates

object CoordinatesAdapter {
    @FromJson
    fun fromJson(coordinates: List<Double>): CoordinatesTO {
        if (coordinates.size != 2) throw IllegalArgumentException("Coordinates must have 2 values")
        return CoordinatesTO(
            latitude = coordinates[0],
            longitude = coordinates[1]
        )
    }

    @ToJson
    fun toJson(coordinates: CoordinatesTO): List<Double> {
        return listOf(coordinates.latitude, coordinates.longitude)
    }
}
