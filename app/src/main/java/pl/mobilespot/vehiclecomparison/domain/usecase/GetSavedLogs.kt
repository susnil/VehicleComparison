package pl.mobilespot.vehiclecomparison.domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.mobilespot.vehiclecomparison.data.local.LogDao
import pl.mobilespot.vehiclecomparison.domain.model.Log
import javax.inject.Inject

class GetSavedLogs @Inject constructor(
    private val logDao: LogDao
) {

    operator fun invoke(): Flow<List<Log>> {
        return logDao.getLogs()
    }

}