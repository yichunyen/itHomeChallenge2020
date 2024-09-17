package yichunyen.swapi.demo.data

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProfileListResponseTest : BaseTest() {
    private lateinit var response : ProfileListResponse
    @Before
    fun setup(){
        response =  Gson().fromJson(
            readJsonFile("profileList"),
            ProfileListResponse::class.java
        )
    }

    @Test
    fun responseTest(){
        Assert.assertEquals( "82", response.count)
        Assert.assertEquals( "http://swapi.dev/api/people/?page=2", response.next)
        Assert.assertEquals( null, response.previous)
        checkNotNull(response.results)
    }
}