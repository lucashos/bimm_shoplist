package interview.lucashos.sakeshop.shopdetails

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import interview.lucashos.sakeshop.domain.model.SakeShop
import interview.lucashos.sakeshop.navigation.FeaturesNavGraph
import kotlinx.coroutines.flow.collectLatest

@Composable
@Destination<FeaturesNavGraph>
fun ShopDetailsScreen(
    navigator: DestinationsNavigator,
    viewModel: ShopDetailsViewModel = hiltViewModel(),
    shop: SakeShop
) {

    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is ShopDetailsViewModel.UiEvent.Toast -> Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    val state = viewModel.state
    ShopDetailsContent(
        screenName = state.screenName,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ShopDetailsContent(
    screenName: String,
    onEvent: (ShopDetailsEvent) -> Unit
) {
    Column {
        Text(text = screenName)
        Button(onClick = { onEvent(ShopDetailsEvent.ShowToast) }) {
            Text(text = "Show Toast")
        }
    }
}

@Preview
@Composable
private fun PreviewShopDetailsScreen() {
    ShopDetailsContent(
        screenName = "ShopDetails",
        onEvent = {}
    )
}
