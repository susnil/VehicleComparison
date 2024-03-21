package pl.mobilespot.vehiclecomparison.domain.usecase

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import pl.mobilespot.vehiclecomparison.data.local.LogDao
import pl.mobilespot.vehiclecomparison.domain.model.Log

class GetSavedLogsTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke usecase should return list of logs`() = runBlockingTest {
        // given
        val expectedLogs = listOf(
            Log("10:00:00 21-03-2024", "Star Destroyer,CR90 corvette"),
            Log("10:01:00 21-03-2024", "Death Star,CR90 corvette")
        )
        val logDao = mockk<LogDao>()

        every { logDao.getLogs() } returns flowOf(expectedLogs)

        // when
        val getSavedLogs = GetSavedLogs(logDao)
        val result: Flow<List<Log>> = getSavedLogs()

        // then
        assertEquals(expectedLogs, result.first())
    }
}