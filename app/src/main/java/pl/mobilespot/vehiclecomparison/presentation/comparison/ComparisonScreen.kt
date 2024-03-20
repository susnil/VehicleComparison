package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.starship.StarshipsGrid
import timber.log.Timber

@Composable
fun ComparisonScreen(viewModel: ComparisonViewModel = viewModel<ComparisonViewModel>()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val count = uiState.starships.count()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (uiState.starships.isEmpty()) {
            Text(text = stringResource(id = R.string.no_selected_vehicles))
        } else {
            Text(
                text = pluralStringResource(
                    id = R.plurals.compare_selected_vehicles,
                    count, count
                )
            )
            StarshipsGrid(viewModel, uiState.starships.toList(), uiState.metrics)
        }
        if (uiState.starships.count() < 2) {
            Text(text = stringResource(id = R.string.compare_error))
        }
    }

}