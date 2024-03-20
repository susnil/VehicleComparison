package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel
import pl.mobilespot.vehiclecomparison.presentation.starship.StarshipsGrid

@Composable
fun CollectionScreen(comparisonViewModel: ComparisonViewModel = hiltViewModel(), viewModel: CollectionViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.collectionUiState) {
        is CollectionUiState.Loading -> {
            LoadingInfo()
        }

        is CollectionUiState.Error -> ErrorInfo()

        is CollectionUiState.Success ->
            StarshipsGrid(comparisonViewModel, (uiState.collectionUiState as CollectionUiState.Success).starships)

    }
}

@Composable
private fun ErrorInfo() {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Text(stringResource(R.string.loading_error)) }
}

@Composable
private fun LoadingInfo() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
        )
        Text(stringResource(R.string.loading))
    }
}

