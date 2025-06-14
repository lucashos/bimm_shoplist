package interview.lucashos.sakeshop.data.datasource

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import interview.lucashos.sakeshop.data.mappers.toDomain
import interview.lucashos.sakeshop.data.model.SakeShopTO
import interview.lucashos.sakeshop.data.providers.JsonProvider
import interview.lucashos.sakeshop.domain.model.SakeShop
import javax.inject.Inject

class SakeShopDataSource @Inject constructor(
    private val moshi: Moshi,
    private val shopsJsonProvider: JsonProvider
) {
    fun getSakeShops(): List<SakeShop> {
        val type = Types.newParameterizedType(List::class.java, SakeShopTO::class.java)
        val adapter = moshi.adapter<List<SakeShopTO>>(type)
        val data = adapter.fromJson(shopsJsonProvider.provide()) ?: listOf()
        return data.toDomain()
    }
}
