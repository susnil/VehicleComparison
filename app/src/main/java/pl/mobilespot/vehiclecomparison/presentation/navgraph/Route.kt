package pl.mobilespot.vehiclecomparison.presentation.navgraph

enum class Route(
    val route: String
) {
    CollectionScreen(route = "collection"),
    CompareScreen(route = "compare"),
    HistoryScreen(route = "history")
}