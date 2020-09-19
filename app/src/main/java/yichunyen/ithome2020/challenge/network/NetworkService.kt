package yichunyen.ithome2020.challenge.network

import retrofit2.Call
import retrofit2.http.GET
import yichunyen.ithome2020.challenge.data.ProfileList

interface NetworkService {
    @GET("/api/people/")
    fun profileList(): Call<ProfileList>
}