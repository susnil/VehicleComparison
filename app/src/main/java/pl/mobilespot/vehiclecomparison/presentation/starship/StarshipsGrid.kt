package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import pl.mobilespot.vehiclecomparison.domain.model.Starship

@Composable
fun StarshipsGrid(starships: List<Starship>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
    ) {
        items(starships) { starship ->
            StarshipGrid(starship = starship)
        }
    }
}