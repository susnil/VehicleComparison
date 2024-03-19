package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.starship.StarshipsGrid

@Composable
fun CollectionScreen(viewModel: CollectionViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.collectionUiState) {
        is CollectionUiState.Loading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
            )
            Text(stringResource(R.string.loading))
        }

        is CollectionUiState.Error -> Text(stringResource(R.string.loading_error))

        is CollectionUiState.Success ->
            StarshipsGrid((uiState.collectionUiState as CollectionUiState.Success).starships)

    }
}

