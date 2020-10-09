package yichunyen.swapi.demo.network

import retrofit2.Call
import retrofit2.http.GET
import yichunyen.swapi.demo.data.FilmResponse
import yichunyen.swapi.demo.data.ProfileListResponse

interface NetworkService {
    @GET("/api/people/")
    fun profileList(): Call<ProfileListResponse>

    @GET("/api/films/")
    fun films(): Call<FilmResponse>
}