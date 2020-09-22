package yichunyen.ithome2020.challenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager private constructor() {
    companion object {
        private const val BASE_URL = "https://swapi.dev/"
        private var instance: NetworkManager? = null
        private var retrofit: Retrofit? = null
        val client: NetworkService
            get() {
                if (instance == null) {
                    instance = NetworkManager()
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build()
                }

                return retrofit!!.create(NetworkService::class.java)
            }

        fun clear() {
            instance = null
        }
    }
}