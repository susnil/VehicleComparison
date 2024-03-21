package pl.mobilespot.vehiclecomparison.core.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mobilespot.vehiclecomparison.core.Constants
import pl.mobilespot.vehiclecomparison.data.local.LogDao
import pl.mobilespot.vehiclecomparison.data.local.LogDatabase
import pl.mobilespot.vehiclecomparison.data.mapper.StarShipMapper
import pl.mobilespot.vehiclecomparison.data.remote.StarshipApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInstance(): StarshipApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarshipApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMapperInstance(): StarShipMapper {
        return StarShipMapper()
    }

    @Provides
    @Singleton
    fun provideLogDatabase(
        application: Application
    ): LogDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = LogDatabase::class.java,
            name = "log_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLogDao(
        logDatabase: LogDatabase
    ): LogDao = logDatabase.logDao()

}