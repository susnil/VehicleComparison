package pl.mobilespot.vehiclecomparison.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.collection.CollectionScreen
import pl.mobilespot.vehiclecomparison.presentation.common.BottomNavigation
import pl.mobilespot.vehiclecomparison.presentation.common.BottomNavigationItem
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.VehicleComparisonTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val selectedItem by rememberSaveable {
                mutableIntStateOf(0)
            }
            val bottomNavigationItems = remember {
                listOf(
                    BottomNavigationItem(icon = R.drawable.ic_elements, text = "Collection"),
                    BottomNavigationItem(icon = R.drawable.ic_compare, text = "Compare"),
                    BottomNavigationItem(icon = R.drawable.ic_history, text = "History"),
                )
            }
            VehicleComparisonTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(), content = { paddingValues ->
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(paddingValues)) {
                            CollectionScreen()
                        }
                    }, bottomBar = {

                        BottomNavigation(
                            items = bottomNavigationItems,
                            selectedItem = selectedItem,
                            onItemClick = { index ->
                                when (index) {
//                                        0 -> navigateToTab(
//                                            navController = navController,
//                                            route = Route.HomeScreen.route
//                                        )
//
//                                        1 -> navigateToTab(
//                                            navController = navController,
//                                            route = Route.SearchScreen.route
//                                        )
//
//                                        2 -> navigateToTab(
//                                            navController = navController,
//                                            route = Route.BookmarkScreen.route
//                                        )
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VehicleComparisonTheme {
        Greeting("Android")
    }
}