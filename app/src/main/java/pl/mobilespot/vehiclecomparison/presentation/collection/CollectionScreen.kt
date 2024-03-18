package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CollectionScreen(viewModel: CollectionViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.collectionUiState) {
        is CollectionUiState.Loading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
            )
        }

        CollectionUiState.Error -> Text("Error")
        is CollectionUiState.Success -> Text("Data")
    }

}