package pl.mobilespot.vehiclecomparison.presentation.comparison

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.pluralStringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.mobilespot.vehiclecomparison.R
import timber.log.Timber

@Composable
fun ComparisonScreen(viewModel: ComparisonViewModel = viewModel<ComparisonViewModel>()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    //val count2 by viewModel.activeUsers.collectAsStateWithLifecycle()
    Timber.d("Count: $uiState ${uiState.set}")//$count2")
    Column {
        Text(text = pluralStringResource(id = R.plurals.compare_selected_vehicles, uiState.set.count()))
        uiState.set.forEach {
            Text(text = it.name)
        }
    }

}