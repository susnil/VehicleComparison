package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pl.mobilespot.vehiclecomparison.core.FakeData
import pl.mobilespot.vehiclecomparison.domain.model.Starship

class StarshipPreviewParameterProvider : PreviewParameterProvider<Starship> {
    override val values: Sequence<Starship> = sequenceOf(FakeData.starship)

}