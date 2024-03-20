package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mobilespot.vehiclecomparison.domain.model.MinMaxMetrics
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.size

@Composable
fun StarshipsGrid(
    comparisonViewModel: ComparisonViewModel = hiltViewModel(),
    starships: List<Starship>,
    metrics: MinMaxMetrics? = null
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(MaterialTheme.size.minGridSize),
    ) {
        items(starships) { starship ->
            StarshipDetailsScreen(comparisonViewModel, starship = starship, metrics)
        }
    }
}