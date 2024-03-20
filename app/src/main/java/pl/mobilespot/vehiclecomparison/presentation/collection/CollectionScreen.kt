package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.size
import pl.mobilespot.vehiclecomparison.presentation.starship.StarshipDetailsScreen

@Composable
fun CollectionScreen(
    comparisonViewModel: ComparisonViewModel = hiltViewModel(),
    viewModel: CollectionViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.starships.collectAsLazyPagingItems()

    val handlePagingResult = handlePagingResult(pagingItems)
    if (handlePagingResult) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(MaterialTheme.size.minGridSize),
        ) {
            items(count = pagingItems.itemCount,
                key = pagingItems.itemKey { it.name }) { index ->
                pagingItems[index]?.let {
                    StarshipDetailsScreen(
                        comparisonViewModel,
                        starship = it
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Starship>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            LoadingInfo()
            false
        }

        error != null -> {
            ErrorInfo()
            false
        }

        else -> {
            true
        }
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

