package pl.mobilespot.vehiclecomparison.presentation.collection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import pl.mobilespot.vehiclecomparison.presentation.comparison.ComparisonViewModel
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.MSIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding
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
        Box(Modifier.fillMaxSize()) {
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
            var isFilterDialogShown by remember {
                mutableStateOf(false)
            }
            val form by viewModel.filterForm.collectAsStateWithLifecycle()
            if (isFilterDialogShown) {

                FilterDialog(
                    {
                        viewModel.clearFilter()
                        isFilterDialogShown = false
                    },
                    { name, manufacturer ->
                        viewModel.searchName(name, manufacturer)
                        isFilterDialogShown = false
                    },
                    MSIcon.FilterEnable,
                    form
                )
            }

            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                FloatingActionButton(
                    onClick = { isFilterDialogShown = true },
                    Modifier.padding(MaterialTheme.padding.medium),
                ) {
                    Icon(
                        if (form.isEmpty()) MSIcon.FilterDisable else MSIcon.FilterEnable,
                        "Filter"
                    )
                }
            }
        }
    }
}

@Composable
private fun handlePagingResult(articles: LazyPagingItems<Starship>): Boolean {
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


@Composable
fun FilterDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String) -> Unit,
    icon: ImageVector,
    form: FilterForm,
) {
    var nameTextField by remember {
        mutableStateOf(form.name)
    }
    var manufacturerTextField by remember {
        mutableStateOf(form.manufacturer)
    }
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = stringResource(id = R.string.filter))
        },
        title = {
            Text(stringResource(id = R.string.filter))
        },
        text = {
            Column {
                Text(stringResource(id = R.string.attribute_name))
                TextField(
                    value = nameTextField,
                    onValueChange = { nameTextField = it })
                Text(stringResource(id = R.string.attribute_manufacturer))
                TextField(
                    value = manufacturerTextField,
                    onValueChange = { manufacturerTextField = it })
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(nameTextField, manufacturerTextField)
                }
            ) {
                Text(stringResource(id = R.string.apply))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.dismiss))
            }
        }
    )
}
