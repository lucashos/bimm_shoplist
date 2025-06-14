package interview.lucashos.sakeshop.domain.repository

import interview.lucashos.sakeshop.domain.model.SakeShop

interface SakeShopRepository {

    fun getSakeShops(): List<SakeShop>

}