package yichunyen.swapi.demo.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import yichunyen.swapi.demo.data.FilmResponse
import yichunyen.swapi.demo.data.ProfileListResponse

interface NetworkService {
    @GET("/api/people/")
    fun profileList(): Call<ProfileListResponse>

    @GET("/api/people/")
    fun profileList(
        @Query("page") page: Int
    ) : Call<ProfileListResponse>

    @GET("/api/films/")
    fun films(): Call<FilmResponse>
}
