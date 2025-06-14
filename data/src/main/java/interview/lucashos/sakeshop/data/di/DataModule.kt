package interview.lucashos.sakeshop.data.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import interview.lucashos.sakeshop.data.adapters.CoordinatesAdapter
import interview.lucashos.sakeshop.data.datasource.SakeShopDataSource
import interview.lucashos.sakeshop.data.providers.JsonProvider
import interview.lucashos.sakeshop.data.providers.ShopListJsonProvider
import interview.lucashos.sakeshop.data.repository.SakeShopRepositoryImpl
import interview.lucashos.sakeshop.domain.repository.SakeShopRepository
import interview.lucashos.sakeshop.domain.usecase.ListSakeShopsUseCase

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(CoordinatesAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideSakeShopsJsonProvider(application: Application): JsonProvider = ShopListJsonProvider(application)

    @Provides
    fun provideSakeShopRepository(
        dataSource: SakeShopDataSource
    ): SakeShopRepository = SakeShopRepositoryImpl(
        dataSource = dataSource
    )

    @Provides
    fun provideListSakeShopsUseCase(
        repository: SakeShopRepository
    ) = ListSakeShopsUseCase(
        repository = repository
    )
}