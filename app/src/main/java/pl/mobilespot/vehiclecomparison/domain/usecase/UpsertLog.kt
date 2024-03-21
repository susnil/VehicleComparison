package pl.mobilespot.vehiclecomparison.domain.usecase

import android.icu.text.SimpleDateFormat
import pl.mobilespot.vehiclecomparison.data.local.LogDao
import pl.mobilespot.vehiclecomparison.domain.model.Log
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class UpsertLog @Inject constructor(
    private val logDao: LogDao
) {

    suspend operator fun invoke(starships: Set<Starship>) {
        val names = starships.map { it.name }.sorted()
        val description = names.joinToString(separator = ",")

        val formattedTime =
            SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault()).format(Date())

        val log = Log(description = description, createdAt = formattedTime)
        return logDao.upsert(log)
    }

}