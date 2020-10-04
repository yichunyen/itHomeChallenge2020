package yichunyen.ithome2020.challenge.network

import retrofit2.Call
import retrofit2.http.GET
import yichunyen.ithome2020.challenge.data.FilmResponse
import yichunyen.ithome2020.challenge.data.ProfileListResponse

interface NetworkService {
    @GET("/api/people/")
    fun profileList(): Call<ProfileListResponse>

    @GET("/api/films/")
    fun films(): Call<FilmResponse>
}