package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import pl.mobilespot.vehiclecomparison.core.FakeData.starship
import pl.mobilespot.vehiclecomparison.domain.model.Starship

@Composable
fun StarshipGrid(starship: Starship) {
    Column {
        Text(starship.name)
        Text(starship.model)
        Text(starship.starshipClass)
    }
}

@Preview
@Composable
fun StarshipGridPreview(
    @PreviewParameter(StarshipPreviewParameterProvider::class)
    starship: Starship,
) {
    StarshipGrid(starship)
}