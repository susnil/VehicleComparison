package pl.mobilespot.vehiclecomparison.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mobilespot.vehiclecomparison.data.repository.StarshipRepositoryImpl
import pl.mobilespot.vehiclecomparison.domain.repository.StarshipRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStarshipRepository(starshipRepositoryImpl: StarshipRepositoryImpl): StarshipRepository

}