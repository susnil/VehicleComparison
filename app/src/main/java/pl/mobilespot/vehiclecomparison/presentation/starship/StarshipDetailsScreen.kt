package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.LocalIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.MSIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding

@Composable
fun StarshipDetailsScreen(starship: Starship)  {
    Card(Modifier.padding(MaterialTheme.padding.small)) {
        Column(Modifier.padding(MaterialTheme.padding.small)) {
            Text(starship.name, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis)
            Text(starship.model)
            Text(starship.starshipClass, fontStyle = FontStyle.Italic)
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = null,
                modifier = Modifier.size(LocalIcon.current.normal),
                tint = colorResource(id = R.color.body)
            )
            Icon(
                imageVector = MSIcon.Tag,
                contentDescription = null,
                modifier = Modifier.size(LocalIcon.current.normal),
                tint = colorResource(id = R.color.body)
            )
        }
    }
}

@Preview
@Composable
fun StarshipDetailsScreenPreview(
    @PreviewParameter(StarshipPreviewParameterProvider::class, limit = 1)
    starship: Starship,
) {
    StarshipDetailsScreen(starship)
}