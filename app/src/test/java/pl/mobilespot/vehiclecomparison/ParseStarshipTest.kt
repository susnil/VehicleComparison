package pl.mobilespot.vehiclecomparison

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.mobilespot.vehiclecomparison.data.remote.dto.StarshipDto


class ParseStarshipTest {
    var json = "{\n" +
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

    @Test
    fun convertObjectTest() {
        val convertedObject = Gson().fromJson(
            json,
            StarshipDto::class.java
        )
        assertEquals(1, convertedObject.films.count())
    }


}