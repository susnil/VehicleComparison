package pl.mobilespot.vehiclecomparison.data.mapper

import pl.mobilespot.vehiclecomparison.collection.Starship
import pl.mobilespot.vehiclecomparison.data.remote.dto.StarshipDto
import javax.inject.Inject

class StarShipMapper @Inject constructor() : Mapper<StarshipDto, Starship> {

    override fun fromDto(dto: StarshipDto): Starship {
        return Starship(dto.name, dto.model, dto.starshipClass, dto.manufacturer.split(","))
    }

}