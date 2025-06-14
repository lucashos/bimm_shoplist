package interview.lucashos.sakeshop.data.mappers

import interview.lucashos.sakeshop.data.model.CoordinatesTO
import interview.lucashos.sakeshop.domain.model.Coordinates

fun CoordinatesTO.toDomain() = Coordinates(
    latitude = latitude,
    longitude = longitude
)