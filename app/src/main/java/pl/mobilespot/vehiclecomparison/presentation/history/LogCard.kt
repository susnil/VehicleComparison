package pl.mobilespot.vehiclecomparison.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.vehiclecomparison.domain.model.Log
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding

@Composable
fun LogCard(log: Log) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.padding.small)
    ) {
        Column(
            Modifier.padding(MaterialTheme.padding.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = log.createdAt, style = MaterialTheme.typography.headlineMedium)
            HorizontalDivider()
            log.description.let { description ->
                val list = description.split(",")
                list.forEachIndexed { index, element ->
                    Text(text = element, style = MaterialTheme.typography.bodyLarge)
                    if (index < list.size - 1) {
                        Text("vs", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LogCardPreview() {
    LogCard(log = Log("10:00:00 21-03-2024", "Star Destroyer,CR90 corvette"))
}