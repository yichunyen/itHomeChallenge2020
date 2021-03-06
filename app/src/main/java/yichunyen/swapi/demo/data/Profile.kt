package yichunyen.swapi.demo.data

import com.google.gson.annotations.SerializedName
import yichunyen.swapi.demo.data.Gender.UNKNOWN
import yichunyen.swapi.demo.data.Gender.values

/**
 * {
 *      "name": "Luke Skywalker",
 *      "height": "172",
 *      "mass": "77",
 *      "hair_color": "blond",
 *      "skin_color": "fair",
 *      "eye_color": "blue",
 *      "birth_year": "19BBY",
 *      "gender": "male",
 *      "homeworld": "https://swapi.dev/api/planets/1/",
 *      "films": [
 *          "https://swapi.dev/api/films/2/",
 *          "https://swapi.dev/api/films/6/",
 *          "https://swapi.dev/api/films/3/",
 *          "https://swapi.dev/api/films/1/",
 *          "https://swapi.dev/api/films/7/"
 *      ],
 *      "species": [
 *          "https://swapi.dev/api/species/1/"
 *      ],
 *      "vehicles": [
 *          "https://swapi.dev/api/vehicles/14/",
 *          "https://swapi.dev/api/vehicles/30/"
 *      ],
 *      "starships": [
 *          "https://swapi.dev/api/starships/12/",
 *          "https://swapi.dev/api/starships/22/"
 *      ],
 *      "created": "2014-12-09T13:50:51.644000Z",
 *      "edited": "2014-12-20T21:17:56.891000Z",
 *      "url": "https://swapi.dev/api/people/1/"
 * }
 *
 */
data class Profile(
    val name: String,
    val height: String,
    @SerializedName("mass") val weight: String,
    private val gender: String,
    @SerializedName("birth_year") val birthYear: String,
    private val films: List<String>
) {
    val genderType: Gender
        get() {
            values().forEach {
                if (it.type == gender) {
                    return it
                }
            }
            return UNKNOWN
        }

    val filmIds: ArrayList<String>
        get() {
            val list = arrayListOf<String>()
            films.forEach {
                url->
                list.add(
                    url.filter {
                        it.isDigit()
                    }
                )
            }
            return list
        }
}
