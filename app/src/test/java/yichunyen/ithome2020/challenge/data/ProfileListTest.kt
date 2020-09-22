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
                    Assert.assertEquals("172", it.height)
                    Assert.assertEquals("77", it.weight)
                    Assert.assertEquals(Gender.MALE, it.genderType)
                    Assert.assertEquals("19BBY", it.birthYear)
                }
                "C-3PO" -> {
                    Assert.assertEquals("167", it.height)
                    Assert.assertEquals("75", it.weight)
                    Assert.assertEquals(Gender.NONE, it.genderType)
                    Assert.assertEquals("112BBY", it.birthYear)
                }
                "R2-D2" -> {
                    Assert.assertEquals("96", it.height)
                    Assert.assertEquals("32", it.weight)
                    Assert.assertEquals(Gender.NONE, it.genderType)
                    Assert.assertEquals("33BBY", it.birthYear)
                }
                "Darth Vader" -> {
                    Assert.assertEquals("202", it.height)
                    Assert.assertEquals("136", it.weight)
                    Assert.assertEquals(Gender.MALE, it.genderType)
                    Assert.assertEquals("41.9BBY", it.birthYear)
                }
                "Leia Organa" -> {
                    Assert.assertEquals("150", it.height)
                    Assert.assertEquals("49", it.weight)
                    Assert.assertEquals(Gender.FEMALE, it.genderType)
                    Assert.assertEquals("19BBY", it.birthYear)
                }
                "Owen Lars" -> {
                    Assert.assertEquals("178", it.height)
                    Assert.assertEquals("120", it.weight)
                    Assert.assertEquals(Gender.MALE, it.genderType)
                    Assert.assertEquals("52BBY", it.birthYear)
                }
                "Beru Whitesun lars" -> {
                    Assert.assertEquals("165", it.height)
                    Assert.assertEquals("75", it.weight)
                    Assert.assertEquals(Gender.FEMALE, it.genderType)
                    Assert.assertEquals("47BBY", it.birthYear)
                }
                "R5-D4" -> {
                    Assert.assertEquals("97", it.height)
                    Assert.assertEquals("32", it.weight)
                    Assert.assertEquals(Gender.NONE, it.genderType)
                    Assert.assertEquals("unknown", it.birthYear)
                }
                "Biggs Darklighter" -> {
                    Assert.assertEquals("183", it.height)
                    Assert.assertEquals("84", it.weight)
                    Assert.assertEquals(Gender.MALE, it.genderType)
                    Assert.assertEquals("24BBY", it.birthYear)
                }
                "Obi-Wan Kenobi" -> {
                    Assert.assertEquals("182", it.height)
                    Assert.assertEquals("77", it.weight)
                    Assert.assertEquals(Gender.MALE, it.genderType)
                    Assert.assertEquals("57BBY", it.birthYear)
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
}