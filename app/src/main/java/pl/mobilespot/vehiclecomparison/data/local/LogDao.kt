package pl.mobilespot.vehiclecomparison.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.vehiclecomparison.domain.model.Log

@Dao
interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(log: Log)

    @Query("SELECT * FROM Log ORDER BY id DESC")
    fun getLogs(): Flow<List<Log>>
}