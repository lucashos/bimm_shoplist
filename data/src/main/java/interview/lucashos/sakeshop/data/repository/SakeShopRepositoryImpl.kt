package interview.lucashos.sakeshop.data.repository

import interview.lucashos.sakeshop.data.datasource.SakeShopDataSource
import interview.lucashos.sakeshop.domain.model.SakeShop
import interview.lucashos.sakeshop.domain.repository.SakeShopRepository
import javax.inject.Inject

class SakeShopRepositoryImpl @Inject constructor(
    private val dataSource: SakeShopDataSource
): SakeShopRepository {
    override fun getSakeShops() = dataSource.getSakeShops()
}