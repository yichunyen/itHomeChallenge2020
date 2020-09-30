package yichunyen.ithome2020.challenge.data

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class ProfileListTest : BaseTest() {

    @Test
    fun listTest(){
        val list = getList()
        list.results.forEach {
            when (it.name) {
                "Luke Skywalker" -> {
                    checkProfile(
                        it,
                        "172",
                        "77",
                        Gender.MALE,
                        "19BBY",
                        arrayListOf("1", "2", "3", "6")
                    )
                }
                "C-3PO" -> {
                    checkProfile(
                        it,
                        "167",
                        "75",
                        Gender.NONE,
                        "112BBY",
                        arrayListOf("1", "2", "3", "4", "5", "6")
                    )
                }
                "R2-D2" -> {
                    checkProfile(
                        it,
                        "96",
                        "32",
                        Gender.NONE,
                        "33BBY",
                        arrayListOf("1", "2", "3", "4", "5", "6")
                    )
                }
                "Darth Vader" -> {
                    checkProfile(
                        it,
                        "202",
                        "136",
                        Gender.MALE,
                        "41.9BBY",
                        arrayListOf("1", "2", "3", "6")
                    )
                }
                "Leia Organa" -> {
                    checkProfile(
                        it,
                        "150",
                        "49",
                        Gender.FEMALE,
                        "19BBY",
                        arrayListOf("1", "2", "3", "6")
                    )
                }
                "Owen Lars" -> {
                    checkProfile(
                        it,
                        "178",
                        "120",
                        Gender.MALE,
                        "52BBY",
                        arrayListOf("1", "5", "6")
                    )
                }
                "Beru Whitesun lars" -> {
                    checkProfile(
                        it,
                        "165",
                        "75",
                        Gender.FEMALE,
                        "47BBY",
                        arrayListOf("1", "5", "6")
                    )
                }
                "R5-D4" -> {
                    checkProfile(
                        it,
                        "97",
                        "32",
                        Gender.NONE,
                        "unknown",
                        arrayListOf("1")
                    )
                }
                "Biggs Darklighter" -> {
                    checkProfile(
                        it,
                        "183",
                        "84",
                        Gender.MALE,
                        "24BBY",
                        arrayListOf("1")
                    )
                }
                "Obi-Wan Kenobi" -> {
                    checkProfile(
                        it,
                        "182",
                        "77",
                        Gender.MALE,
                        "57BBY",
                        arrayListOf("1", "2", "3", "4", "5", "6")
                    )
                }
                else -> {
                    AssertionError("Need to check the profileList.json, here is extra data in list.")
                }

            }
        }
    }

    private fun getList(): ProfileList {
        val jsonRawData = readJsonFile("profileList")

        return Gson().fromJson(
            jsonRawData,
            ProfileList::class.java
        )
    }

    private fun checkProfile(
        profile: Profile,
        height: String,
        weight: String,
        gender: Gender,
        birthYear: String,
        expectedFilmsIds: ArrayList<String>
    ) {
        Assert.assertEquals(height, profile.height)
        Assert.assertEquals(weight, profile.weight)
        Assert.assertEquals(gender, profile.genderType)
        Assert.assertEquals(birthYear, profile.birthYear)
        val filmIds = profile.filmIds
        for (index in 0 until filmIds.size - 1) {
            Assert.assertEquals(expectedFilmsIds[index], filmIds[index])
        }
    }
}