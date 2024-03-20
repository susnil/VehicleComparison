package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.MSIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding
import java.time.format.DateTimeFormatter

@Composable
fun StarshipDetailsScreen(
    comparisonViewModel: ComparisonViewModel = hiltViewModel(),
    starship: Starship,
) {
    var selected by remember { mutableStateOf(comparisonViewModel.hasSelected(starship)) }
    val interactionSource = remember { MutableInteractionSource() }

    Card(Modifier
        .selectable(
            selected = selected,
            interactionSource = interactionSource,
            indication = rememberRipple(),
            enabled = true,
            onClick = {
                comparisonViewModel.select(starship)
                selected = !selected
            }
        )
        .padding(MaterialTheme.padding.small)) {
        Column(Modifier.padding(MaterialTheme.padding.small)) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    starship.name,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.End
                )
                Text(starship.model, style = MaterialTheme.typography.titleMedium)
                Text(starship.starshipClass, fontStyle = FontStyle.Italic)
            }

            Column {
                starship.manufacturer.forEach { manufacturer ->
                    StarshipAttribute(value = manufacturer, image = MSIcon.Tag)
                }
            }

            StarshipAttribute(
                placeholder = R.string.attribute_cargo_capacity,
                value = "${starship.cargoCapacity} kg",
                image = MSIcon.Capacity
            )

            StarshipAttribute(
                placeholder = R.string.attribute_cost_in_credits,
                value = "${starship.costInCredits}",
                image = MSIcon.Money
            )
            StarshipAttribute(
                placeholder = R.string.attribute_max_atmosphering_speed,
                value = "${starship.maxAtmospheringSpeed ?: "N/A"}",
                image = MSIcon.Speed
            )

            StarshipAttribute(
                placeholder = R.string.attribute_consumables,
                value = readablePeriod(starship),
                image = MSIcon.Consumables
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_crew,
                    value = "${starship.crew}",
                    image = MSIcon.Crew
                )
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_passengers,
                    value = "${starship.passengers}",
                    image = MSIcon.Passengers
                )
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_length,
                    value = "${starship.length} meters",
                    image = MSIcon.Length
                )
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_hyperdrive_rating,
                    value = "${starship.hyperdriveRating}",
                    image = MSIcon.Rating
                )
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_films,
                    value = "" + starship.films,
                    image = MSIcon.Films
                )
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_pilots,
                    value = "" + starship.pilots,
                    image = MSIcon.Pilots
                )
            }
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_created,
                    value = starship.created.format(outputFormatter),
                    image = MSIcon.Time
                )
                StarshipAttribute(
                    Modifier.weight(1f),
                    R.string.attribute_edited,
                    value = starship.edited.format(outputFormatter),
                    image = MSIcon.Time
                )
            }
        }
    }
}

@Composable
private fun readablePeriod(starship: Starship): String {
    val period = starship.consumables ?: return "N/A"
    val years = period.years
    val months = period.months
    val days = period.days

    val result = StringBuilder()
    appendPeriod(result, years, "year")
    appendPeriod(result, months, "month")
    appendPeriod(result, days, "day")
    return result.toString()
}

private fun appendPeriod(result: StringBuilder, value: Int, name: String) {
    if (value == 1) {
        result.append("$value $name")
    } else if (value > 1) {
        result.append("$value ${name}s")
    }
}


@Preview
@Composable
fun StarshipDetailsScreenPreview(
    @PreviewParameter(StarshipPreviewParameterProvider::class, limit = 1)
    starship: Starship,
) {
    StarshipDetailsScreen(starship = starship)
}

