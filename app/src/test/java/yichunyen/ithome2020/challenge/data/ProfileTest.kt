package yichunyen.ithome2020.challenge.data

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test


class ProfileTest {

    @Test
    fun skyWalkerTest(){
        val input = "\n" +
                "{\n" +
                "  \"name\": \"Luke Skywalker\",\n" +
                "  \"height\": \"172\",\n" +
                "  \"mass\": \"77\",\n" +
                "  \"hair_color\": \"blond\",\n" +
                "  \"skin_color\": \"fair\",\n" +
                "  \"eye_color\": \"blue\",\n" +
                "  \"birth_year\": \"19BBY\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"homeworld\": \"https://swapi.dev/api/planets/1/\",\n" +
                "  \"films\": [\n" +
                "    \"https://swapi.dev/api/films/2/\",\n" +
                "    \"https://swapi.dev/api/films/6/\",\n" +
                "    \"https://swapi.dev/api/films/3/\",\n" +
                "    \"https://swapi.dev/api/films/1/\",\n" +
                "    \"https://swapi.dev/api/films/7/\"\n" +
                "  ],\n" +
                "  \"species\": [\n" +
                "    \"https://swapi.dev/api/species/1/\"\n" +
                "  ],\n" +
                "  \"vehicles\": [\n" +
                "    \"https://swapi.dev/api/vehicles/14/\",\n" +
                "    \"https://swapi.dev/api/vehicles/30/\"\n" +
                "  ],\n" +
                "  \"starships\": [\n" +
                "    \"https://swapi.dev/api/starships/12/\",\n" +
                "    \"https://swapi.dev/api/starships/22/\"\n" +
                "  ],\n" +
                "  \"created\": \"2014-12-09T13:50:51.644000Z\",\n" +
                "  \"edited\": \"2014-12-20T21:17:56.891000Z\",\n" +
                "  \"url\": \"https://swapi.dev/api/people/1/\"\n" +
                "}"
        val profile = Gson().fromJson(input, Profile::class.java)
        // determine the data
        Assert.assertEquals("Luke Skywalker", profile.name)
        Assert.assertEquals("172", profile.height)
        Assert.assertEquals("77", profile.weight)
        Assert.assertEquals("19BBY", profile.birthYear)
        Assert.assertEquals(Gender.MALE, profile.genderType)
    }
}