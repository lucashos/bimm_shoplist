package interview.lucashos.sakeshop.shoplist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import interview.lucashos.sakeshop.TestActivity
import interview.lucashos.sakeshop.domain.model.Coordinates
import interview.lucashos.sakeshop.domain.model.SakeShop
import org.junit.Rule
import org.junit.Test

class ShopListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun shopList_should_displayAllShops() {
        val shops = listOf(
            SakeShop(
                name = "Shop A",
                address = "Address A",
                rating = 4.5,
                description = "Desc A",
                picture = "",
                coordinates = Coordinates(0.0, 0.0),
                googleMapsLink = "",
                website = ""
            ),
            SakeShop(
                name = "Shop B",
                address = "Address B",
                rating = 4.8,
                description = "Desc B",
                picture = "",
                coordinates = Coordinates(0.0, 0.0),
                googleMapsLink = "",
                website = ""
            )
        )

        composeTestRule.setContent {
            ShopListScaffold(
                shops = shops,
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Shop A").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Address B").assertIsDisplayed()
//        composeTestRule.onNodeWithText("4.5").assertExists()
//
//        composeTestRule.onNodeWithText("Shop B").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Address A").assertIsDisplayed()
//        composeTestRule.onNodeWithText("4.8").assertExists()
    }
}
