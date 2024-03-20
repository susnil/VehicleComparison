package pl.mobilespot.vehiclecomparison.data.mapper

import pl.mobilespot.vehiclecomparison.data.remote.dto.StarshipDto
import pl.mobilespot.vehiclecomparison.domain.model.Starship
import timber.log.Timber
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
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
            parseStringToLong(dto.MGLT.removeSuffix("MGLT").trim())
        )
    }

    private fun parseStringToLong(text: String) = text.toLongOrNull() ?: 0L
    private fun parseStringToOptionalLong(text: String) = text.toLongOrNull()
    private fun parseStringToFloat(text: String) = text.toFloatOrNull() ?: 0.0F
    private fun parseSafetyIsoString(isoString: String): LocalDateTime =
        LocalDateTime.parse(isoString, DateTimeFormatter.ISO_DATE_TIME) ?: LocalDateTime.now()

    private fun parseStringToOptionalPeriod(text: String): Period? {
        val pattern = Pattern.compile("(\\d+)\\s+(month|year|day)s?")
        val matcher = pattern.matcher(text)
        if (!matcher.matches()) return null

        val amount = matcher.group(1).toInt()
        val unit = matcher.group(2)

        val periodUnit = when (unit) {
            "day" -> Period.ofDays(amount)
            "month" -> Period.ofMonths(amount)
            "year" -> Period.ofYears(amount)
            else -> {
                Timber.w(
                    "Invalid Period Input: $text. " +
                            "According to the strategy, N/A will be displayed instead of invalid data."
                )
                return null
            }
        }

        return periodUnit
    }
}
