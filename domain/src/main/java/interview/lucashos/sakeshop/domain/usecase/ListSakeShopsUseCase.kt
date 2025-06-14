package interview.lucashos.sakeshop.domain.usecase

import interview.lucashos.sakeshop.domain.repository.SakeShopRepository

class ListSakeShopsUseCase(
    private val repository: SakeShopRepository
) {
    operator fun invoke() = repository.getSakeShops()
}
