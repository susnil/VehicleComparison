package pl.mobilespot.vehiclecomparison.presentation.history


import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.mobilespot.vehiclecomparison.domain.usecase.GetSavedLogs
import pl.mobilespot.vehiclecomparison.test.common.FakeLogs
import pl.mobilespot.vehiclecomparison.test.common.MainDispatcherRule

class HistoryViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HistoryViewModel
    private val expectedLogs = listOf(
        FakeLogs.log1,
        FakeLogs.log2
    )
    private val getSavedLogs = mockk<GetSavedLogs>()

    @Before
    fun setUp() {
        //given
        every { getSavedLogs.invoke() } returns flowOf(expectedLogs)
    }

    @Test
    fun `init view model should update state with logs`() = runTest {
        //when
        viewModel = HistoryViewModel(getSavedLogs)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }
        //then
        assertTrue(viewModel.state.value.logs.containsAll(expectedLogs))
        collectJob.cancel()
    }
}

