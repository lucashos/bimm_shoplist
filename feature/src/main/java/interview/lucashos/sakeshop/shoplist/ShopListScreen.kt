package interview.lucashos.sakeshop.shoplist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.generated.destinations.ShopDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import interview.lucashos.sakeshop.designsystem.Dimens
import interview.lucashos.sakeshop.designsystem.Dimens.Spacing
import interview.lucashos.sakeshop.designsystem.Yellow
import interview.lucashos.sakeshop.domain.model.SakeShop
import interview.lucashos.sakeshop.feature.R
import interview.lucashos.sakeshop.navigation.FeaturesNavGraph
import kotlinx.coroutines.flow.collectLatest

@Composable
@Destination<FeaturesNavGraph>(start = true)
fun ShopListScreen(
    navigator: DestinationsNavigator,
    viewModel: ShopListViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is ShopListViewModel.UiEvent.ToDetails -> navigator.navigate(ShopDetailsScreenDestination(event.shop))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ShopListEvent.Init)
    }

    val state = viewModel.state
    ShopListScaffold(
        shops = state.shops,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ShopListScaffold(
    shops: List<SakeShop>,
    onEvent: (ShopListEvent) -> Unit
) {
    Scaffold(
        topBar = {
            ShopListAppBar()
        }
    ) { innerPadding ->
        ShopListContent(
            modifier = Modifier.padding(innerPadding),
            shops = shops,
            onEvent = onEvent
        )
    }
}

@Composable
private fun ShopListContent(
    modifier: Modifier = Modifier,
    shops: List<SakeShop>,
    onEvent: (ShopListEvent) -> Unit
) {
    Column(
        modifier = modifier.padding(
            horizontal = Spacing.Medium
        )
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
            contentPadding = PaddingValues(vertical = Spacing.Large)
        ) {
            items(shops) { shop ->
                ShopListItem(onEvent, shop)
            }
        }
    }
}

@Composable
private fun ShopListItem(onEvent: (ShopListEvent) -> Unit, shop: SakeShop) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEvent(ShopListEvent.ToDetails(shop)) }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = Spacing.Small,
                    horizontal = Spacing.Medium
                )
        ) {
            ShopInfo(
                name = shop.name,
                address = shop.address
            )
            Rating(
                rating = shop.rating
            )
        }
    }
}

@Composable
private fun RowScope.ShopInfo(name: String, address: String) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = address,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun Rating(rating: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            tint = Yellow,
            contentDescription = null,
            modifier = Modifier.size(Dimens.Icon.Large)
        )
        Text(
            text = stringResource(R.string.rating_score, rating),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopListAppBar() {
    MediumTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.shop_list_title)
            )
        }
    )
}

@Preview
@Composable
private fun PreviewShopListScreen() {
    val state = ShopListState()
    ShopListContent(
        shops = state.shops,
        onEvent = {}
    )
}
