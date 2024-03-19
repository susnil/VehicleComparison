package pl.mobilespot.vehiclecomparison.data.mapper

import pl.mobilespot.vehiclecomparison.data.remote.dto.StarshipDto
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class StarShipMapper @Inject constructor() : Mapper<StarshipDto, Starship> {

    override fun fromDto(dto: StarshipDto): Starship {
        return Starship(
            dto.name,
            dto.model,
            dto.starshipClass,
            dto.manufacturer.split(",").map { it.trim() },
            dto.films.count(),
            dto.pilots.count(),
            parseStringToLong(dto.cargoCapacity),
            parseStringToLong(dto.costInCredits),
            parseStringToLong(dto.crew),
            parseStringToLong(dto.passengers),
            parseStringToFloat(dto.length),
            parseStringToFloat(dto.hyperdriveRating),
            parseSafetyIsoString(dto.created),
            parseSafetyIsoString(dto.edited),
            parseStringToOptionalLong(dto.maxAtmospheringSpeed),
            parseStringToOptionalPeriod(dto.consumables),
            parseStringToLong(dto.MGLT)
        )
    }

}

internal fun parseStringToLong(text: String) = text.toLongOrNull() ?: 0L
internal fun parseStringToOptionalLong(text: String) = text.toLongOrNull()
internal fun parseStringToFloat(text: String) = text.toFloatOrNull() ?: 0.0F
internal fun parseSafetyIsoString(isoString: String) =
    LocalDateTime.parse(isoString, DateTimeFormatter.ISO_DATE_TIME) ?: LocalDateTime.now()

internal fun parseStringToOptionalPeriod(text: String): Period? =
    runCatching {
        Period.parse(text)
    }.getOrNull()
