package interview.lucashos.sakeshop.navigation

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.generated.navgraphs.FeaturesGraph

@NavHostGraph
annotation class MainNavGraph {
    @ExternalNavGraph<FeaturesGraph>(start = true)
    companion object Includes
}
