package yichunyen.ithome2020.challenge.data

import com.google.gson.Gson
import org.junit.Test

class ProfileListTest : BaseTest() {

    @Test
    fun listTest(){
        val list = getList()
        list.results.forEach {
            println(it)
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