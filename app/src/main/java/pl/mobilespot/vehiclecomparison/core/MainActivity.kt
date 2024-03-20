package pl.mobilespot.vehiclecomparison.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.collection.CollectionScreen
import pl.mobilespot.vehiclecomparison.presentation.common.BottomNavigation
import pl.mobilespot.vehiclecomparison.presentation.common.BottomNavigationItem
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonScreen
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.VehicleComparisonTheme
import pl.mobilespot.vehiclecomparison.presentation.history.HistoryScreen
import pl.mobilespot.vehiclecomparison.presentation.navgraph.Route

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedItem by rememberSaveable {
                mutableIntStateOf(0)
            }
            val navController = rememberNavController()
            val backStackState = navController.currentBackStackEntryAsState().value

            selectedItem = when (backStackState?.destination?.route) {
                Route.CollectionScreen.route -> 0
                Route.CompareScreen.route -> 1
                Route.HistoryScreen.route -> 2
                else -> 0
            }
            val bottomNavigationItems = remember {
                listOf(
                    BottomNavigationItem(icon = R.drawable.ic_elements, text = "Collection"),
                    BottomNavigationItem(icon = R.drawable.ic_compare, text = "Compare"),
                    BottomNavigationItem(icon = R.drawable.ic_history, text = "History"),
                )
            }
            VehicleComparisonTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), content = { paddingValues ->
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(paddingValues)
                        ) {
                            val comparisonViewModel: ComparisonViewModel = hiltViewModel()
                            NavHost(
                                navController = navController,
                                startDestination = Route.CollectionScreen.route,
                            ) {

                                composable(route = Route.CollectionScreen.route) { backStackEntry ->
                                    CollectionScreen(comparisonViewModel)
                                }
                                composable(route = Route.CompareScreen.route) { backStackEntry ->
                                    ComparisonScreen(comparisonViewModel)
                                }
                                composable(route = Route.HistoryScreen.route) { backStackEntry ->
                                    HistoryScreen()
                                }
                            }
                        }
                    }, bottomBar = {

                        BottomNavigation(
                            items = bottomNavigationItems,
                            selectedItem = selectedItem,
                            onItemClick = { index ->
                                when (index) {
                                    0 -> navigateToTab(
                                        navController = navController,
                                        route = Route.CollectionScreen.route
                                    )

                                    1 -> navigateToTab(
                                        navController = navController,
                                        route = Route.CompareScreen.route
                                    )

                                    2 -> navigateToTab(
                                        navController = navController,
                                        route = Route.HistoryScreen.route
                                    )
                                }
                            }
                        )

                    }
                )
            }
        }
//        CoroutineScope(Dispatchers.IO).launch {
//            val result = provideApiInstance().getStarships()
//            println(result)
//        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}