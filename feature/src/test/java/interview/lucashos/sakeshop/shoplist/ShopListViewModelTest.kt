package interview.lucashos.sakeshop.shoplist

import app.cash.turbine.test
import interview.lucashos.sakeshop.domain.model.Coordinates
import interview.lucashos.sakeshop.domain.model.SakeShop
import interview.lucashos.sakeshop.domain.usecase.ListSakeShopsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShopListViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: ShopListViewModel
    private lateinit var listSakeShopsUseCase: ListSakeShopsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        listSakeShopsUseCase = mockk()
        viewModel = ShopListViewModel(
            dispatcher = dispatcher,
            listSakeShopsUseCase = listSakeShopsUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Init event is triggered, state should be updated with shop list`() = runTest {
        // Arrange
        val fakeShops = listOf(
            SakeShop(
                name = "Shop A",
                address = "Address A",
                rating = 4.5,
                description = "description",
                picture = "picture",
                coordinates = Coordinates(0.0, 0.0),
                googleMapsLink = "",
                website = "",
            ),
            SakeShop(
                name = "Shop B",
                address = "Address B",
                rating = 4.5,
                description = "description",
                picture = "picture",
                coordinates = Coordinates(0.0, 0.0),
                googleMapsLink = "",
                website = "",
            )
        )
        coEvery { listSakeShopsUseCase() } returns fakeShops

        // Act
        viewModel.onEvent(ShopListEvent.Init)
        advanceUntilIdle()

        // Assert
        assertEquals(fakeShops, viewModel.state.shops)
    }

    @Test
    fun `when ToDetails event is triggered, should emit UiEvent ToDetails`() = runTest {
        // Arrange
        val shop = SakeShop(
            name = "Shop B",
            address = "Address B",
            rating = 4.5,
            description = "description",
            picture = "picture",
            coordinates = Coordinates(0.0, 0.0),
            googleMapsLink = "",
            website = "",
        )

        // Act + Assert
        viewModel.events.test {
            viewModel.onEvent(ShopListEvent.ToDetails(shop))
            advanceUntilIdle()

            val event = awaitItem()
            assert(event is ShopListViewModel.UiEvent.ToDetails)
            assertEquals(shop, (event as ShopListViewModel.UiEvent.ToDetails).shop)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
