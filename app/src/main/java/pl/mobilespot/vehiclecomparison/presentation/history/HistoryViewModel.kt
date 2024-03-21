package pl.mobilespot.vehiclecomparison.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.mobilespot.vehiclecomparison.domain.usecase.GetSavedLogs
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getSavedLogs: GetSavedLogs
) : ViewModel() {

    private val _state = mutableStateOf(LogState())
    val state: State<LogState> = _state

    init {
        getLogs()
    }

    private fun getLogs() {
        getSavedLogs().onEach {
            _state.value = _state.value.copy(logs = it)
        }.launchIn(viewModelScope)
    }
}