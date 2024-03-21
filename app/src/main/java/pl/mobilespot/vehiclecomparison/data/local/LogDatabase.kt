package pl.mobilespot.vehiclecomparison.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mobilespot.vehiclecomparison.domain.model.Log

@Database(entities = [Log::class], version = 1)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
}