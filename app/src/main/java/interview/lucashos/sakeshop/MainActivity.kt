package interview.lucashos.sakeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.navgraphs.MainGraph
import dagger.hilt.android.AndroidEntryPoint
import interview.lucashos.sakeshops.designsystem.theme.SakeShopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SakeShopTheme {
                val navController = rememberNavController()
                DestinationsNavHost(
                    navController = navController,
                    navGraph = MainGraph
                )
            }
        }
    }
}
