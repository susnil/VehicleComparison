package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding

@Composable
fun StarshipGrid(starship: Starship) {
    Card(Modifier.padding(MaterialTheme.padding.small)) {
        Column {
            Text(starship.name, textAlign = TextAlign.Center)
            Text(starship.model)
            Text(starship.starshipClass, fontStyle = FontStyle.Italic)
        }
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