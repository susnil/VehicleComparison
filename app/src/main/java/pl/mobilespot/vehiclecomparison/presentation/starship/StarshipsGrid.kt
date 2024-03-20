package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel

@Composable
fun StarshipsGrid(comparisonViewModel: ComparisonViewModel = hiltViewModel(),  starships: List<Starship>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(400.dp),
    ) {
        items(starships) { starship ->
            StarshipDetailsScreen(comparisonViewModel, starship = starship)
        }
    }
}