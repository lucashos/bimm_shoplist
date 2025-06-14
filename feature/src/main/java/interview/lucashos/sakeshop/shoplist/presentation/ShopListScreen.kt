package interview.lucashos.sakeshop.shoplist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import interview.lucashos.sakeshop.navigation.FeaturesNavGraph

@Composable
@Destination<FeaturesNavGraph>(start = true)
fun ShopListScreen() {
    Scaffold { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {
            Text(text = "ShopListScreen")
        }
    }
}