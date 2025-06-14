package interview.lucashos.sakeshop.data.mappers

import interview.lucashos.sakeshop.data.model.SakeShopTO
import interview.lucashos.sakeshop.domain.model.SakeShop

fun SakeShopTO.toDomain() = SakeShop(
    name = name,
    description = description,
    picture = picture,
    rating = rating,
    address = address,
    coordinates = coordinates.toDomain(),
    googleMapsLink = googleMapsLink,
    website = website
)

fun List<SakeShopTO>.toDomain() = map { it.toDomain() }