package pl.mobilespot.vehiclecomparison.presentation.navgraph

sealed class Route(
    val route: String
) {
    object CollectionScreen : Route(route = "collection")
    object CompareScreen : Route(route = "compare")
    object HistoryScreen : Route(route = "history")
}