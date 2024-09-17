package yichunyen.swapi.demo.data

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test


class ProfileTest : BaseTest() {
    @Test
    fun genderFemaleTest() {
        val profile = getProfile("profile_female")
        Assert.assertEquals(Gender.FEMALE, profile.genderType)
    }

    @Test
    fun genderMaleTest() {
        val profile = getProfile("profile_male")
        Assert.assertEquals(Gender.MALE, profile.genderType)
    }

    @Test
    fun genderNoneTest() {
        val profile = getProfile("profile_none")
        Assert.assertEquals(Gender.NONE, profile.genderType)
    }

    @Test
    fun filmIdsTest() {
        val filmIds = getProfile("profile_filmIds").filmIds
        Assert.assertEquals("1", filmIds[0])
        Assert.assertEquals("2", filmIds[1])
        Assert.assertEquals("3", filmIds[2])
        Assert.assertEquals("6", filmIds[3])
    }

    private fun getProfile(fileName: String): Profile {
        return Gson().fromJson(
            readJsonFile(fileName),
            Profile::class.java
        )
    }
}