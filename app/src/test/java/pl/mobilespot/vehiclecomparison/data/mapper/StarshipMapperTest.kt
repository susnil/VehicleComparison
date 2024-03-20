package pl.mobilespot.vehiclecomparison.data.mapper

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pl.mobilespot.vehiclecomparison.data.remote.dto.StarshipDto

class StarshipMapperTest {
    private var json = "{\n" +
            "    \"MGLT\": \"10 MGLT\",\n" +
            "    \"cargo_capacity\": \"1000000000000\",\n" +
            "    \"consumables\": \"3 years\",\n" +
            "    \"cost_in_credits\": \"1000000000000\",\n" +
            "    \"created\": \"2014-12-10T16:36:50.509000Z\",\n" +
            "    \"crew\": \"342953\",\n" +
            "    \"edited\": \"2014-12-10T16:36:50.509000Z\",\n" +
            "    \"hyperdrive_rating\": \"4.0\",\n" +
            "    \"length\": \"120000\",\n" +
            "    \"manufacturer\": \"Imperial Department of Military Research, Sienar Fleet Systems\",\n" +
            "    \"max_atmosphering_speed\": \"n/a\",\n" +
            "    \"model\": \"DS-1 Orbital Battle Station\",\n" +
            "    \"name\": \"Death Star\",\n" +
            "    \"passengers\": \"843342\",\n" +
            "    \"films\": [\n" +
            "        \"https://swapi.dev/api/films/1/\"\n" +
            "    ],\n" +
            "    \"pilots\": [],\n" +
            "    \"starship_class\": \"Deep Space Mobile Battlestation\",\n" +
            "    \"url\": \"https://swapi.dev/api/starships/9/\"\n" +
            "}"

    private val starshipDto: StarshipDto = Gson().fromJson(
        json,
        StarshipDto::class.java
    )

    private lateinit var starShipMapper: StarShipMapper

    @Before
    fun setUp() {
        starShipMapper = StarShipMapper()
    }

    @Test
    fun `verify converted StarshipDto`() {
        assertEquals("3 years", starshipDto.consumables)
        assertEquals(1, starshipDto.films.count())
        assertEquals("https://swapi.dev/api/films/1/", starshipDto.films[0])
    }

    @Test
    fun `verify converted StarshipDto to Starship`() {
        val starship = starShipMapper.fromDto(starshipDto)

        assertEquals(1, starship.films)
        assertEquals(2, starship.manufacturer.count())
        assertEquals(120000.0F, starship.length)
        assertEquals(342953, starship.crew)
        assertEquals(843342, starship.passengers)
        assertEquals(10, starship.MGLT)

        assertEquals(null, starship.maxAtmospheringSpeed)
    }
}