package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.LocalIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.MSIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding
import timber.log.Timber

@Composable
fun StarshipDetailsScreen(starship: Starship) {
    var selected by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Card(Modifier
        .selectable(
            selected = selected,
            interactionSource = interactionSource,
            indication = rememberRipple(),
            enabled = true,
            onClick = {
                selected = !selected
                Timber.d("Click, $selected")
            }
        )
        .padding(MaterialTheme.padding.small)) {
        Column(Modifier.padding(MaterialTheme.padding.small)) {
            Text(starship.name)
            Text(starship.model)
            Text(starship.starshipClass, fontStyle = FontStyle.Italic)
            LazyRow {
                items(starship.manufacturer) { manufacturer ->
                    StarshipAttribute(text = manufacturer, image = MSIcon.Tag)
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StarshipAttribute(text = "" + starship.films, image = MSIcon.Films, Modifier.weight(1f))
                StarshipAttribute(text = "" + starship.pilots, image = MSIcon.Pilots, Modifier.weight(1f))
            }

            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(LocalIcon.current.normal),
                    tint = colorResource(id = R.color.body)
                )
                Text("Created: Time")
            }
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

