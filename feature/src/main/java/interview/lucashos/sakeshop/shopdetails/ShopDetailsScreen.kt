package interview.lucashos.sakeshop.shopdetails

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import interview.lucashos.sakeshop.designsystem.Dimens
import interview.lucashos.sakeshop.designsystem.Dimens.Spacing
import interview.lucashos.sakeshop.domain.model.Coordinates
import interview.lucashos.sakeshop.domain.model.SakeShop
import interview.lucashos.sakeshop.feature.R
import interview.lucashos.sakeshop.navigation.FeaturesNavGraph
import kotlinx.coroutines.flow.collectLatest
import androidx.core.net.toUri

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
                ShopDetailsViewModel.UiEvent.Back -> navigator.navigateUp()
                is ShopDetailsViewModel.UiEvent.OpenMaps -> context.openMaps(event.address, event.coordinates)
                is ShopDetailsViewModel.UiEvent.OpenWebsite -> context.openWebsite(event.website)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ShopDetailsEvent.Init(shop))
    }

    val state = viewModel.state
    ShopDetailsScaffold(
        shop = state.shop,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ShopDetailsScaffold(
    shop: SakeShop?,
    onEvent: (ShopDetailsEvent) -> Unit
) {
    if (shop == null) return

    Scaffold(
        topBar = {
            ShopDetailsAppBar(
                name = shop.name,
                rating = shop.rating
            ) {
                onEvent(ShopDetailsEvent.Back)
            }
        }
    ) { innerPadding ->
        ShopDetailsContent(
            modifier = Modifier.padding(innerPadding),
            shop = shop,
            onEvent = onEvent
        )
    }
}

@Composable
private fun ShopDetailsContent(
    modifier: Modifier = Modifier,
    shop: SakeShop,
    onEvent: (ShopDetailsEvent) -> Unit
) {
    Column(
        modifier = modifier.padding(
            vertical = Spacing.Large,
            horizontal = Spacing.Medium
        )
    ) {
        ShopPicture(url = shop.picture)
        Spacer(modifier = Modifier.height(Spacing.Medium))
        ShopDescription(description = shop.description)
        Spacer(modifier = Modifier.height(Spacing.Large))
        ShopAddress(address = shop.address) {
            onEvent(ShopDetailsEvent.OpenAddress(shop.address, shop.coordinates))
        }
        Spacer(modifier = Modifier.height(Spacing.Medium))
        Website(website = shop.website) {
            onEvent(ShopDetailsEvent.OpenWebsite(shop.website))
        }
    }
}

@Composable
private fun RowScope.ShopName(name: String) {
    Text(
        modifier = Modifier.weight(1f),
        text = name,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun ColumnScope.ShopPicture(url: String?) {
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(url)
            .error(R.drawable.ic_placeholder)
            .crossfade(true)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(Dimens.Image.XXLarge)
            .clip(RoundedCornerShape(Dimens.CornerRadius.Large))
            .clipToBounds()
            .align(Alignment.CenterHorizontally)
    )
}

@Composable
private fun ShopDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun ShopRating(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            tint = interview.lucashos.sakeshop.designsystem.Yellow,
            contentDescription = null,
            modifier = Modifier.size(Dimens.Icon.Medium)
        )
        Text(
            text = stringResource(R.string.rating_score, rating),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun ShopAddress(address: String, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_pin_point),
            contentDescription = null,
            modifier = Modifier.size(Dimens.Icon.Medium)
        )
        Text(
            text = address,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun Website(website: String, onClick: () -> Unit) {
    if (website.isBlank()) return
    Button(
        onClick = { onClick() }
    ) {
        Text(
            text = "Open Website",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShopDetailsAppBar(
    name: String,
    rating: Double,
    onBack: () -> Unit
) {
    MediumTopAppBar(
        title = {
            Row(
                horizontalArrangement = SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Spacing.Medium
                    )
            ) {
                ShopName(name = name)
                ShopRating(rating = rating)
            }
        },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .size(Spacing.XLarge)
                    .clip(CircleShape)
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(Dimens.Icon.Medium),
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

private fun Context.openMaps(address: String, coordinates: Coordinates) {
    val uri = "geo:${coordinates.latitude},${coordinates.longitude}?q=$address"
    val intent = android.content.Intent(
        android.content.Intent.ACTION_VIEW,
        uri.toUri()
    )
    startActivity(intent)
}

private fun Context.openWebsite(website: String) {
    val intent = android.content.Intent(
        android.content.Intent.ACTION_VIEW,
        website.toUri()
    )
    startActivity(intent)
}

@Preview
@Composable
private fun PreviewShopDetailsScreen() {
    val state = ShopDetailsState(
        shop = SakeShop(
            name = "Sake Name",
            description = "Sake Description",
            picture = null,
            rating = 4.5,
            address = "Sake Address",
            coordinates = Coordinates(0.0, 0.0),
            googleMapsLink = "",
            website = ""
        )
    )
    ShopDetailsContent(
        shop = state.shop!!,
        onEvent = {}
    )
}
