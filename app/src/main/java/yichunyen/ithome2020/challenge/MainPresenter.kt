package yichunyen.ithome2020.challenge

import okhttp3.ResponseBody
import retrofit2.Call
import yichunyen.ithome2020.challenge.data.FilmResponse
import yichunyen.ithome2020.challenge.data.ProfileList
import yichunyen.ithome2020.challenge.network.NetworkCallback
import yichunyen.ithome2020.challenge.network.NetworkManager

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    init {
        view.setPresenter(this)
    }

    private val profileListCall = NetworkManager.client.profileList()
    private val filmCall = NetworkManager.client.films()

    override fun fetchData() {
        getProfileList()
        getFilms()
    }

    private fun getProfileList(){
        profileListCall.enqueue(object : NetworkCallback<ProfileList>() {
            override fun onSuccess(response: ProfileList) {
                view.showProfileList(response.results)
            }

            override fun onFailure(
                call: Call<ProfileList>,
                statusCode: Int,
                errorBody: ResponseBody?
            ) {
                view.showApiError("statusCode= $statusCode & errorBody= $errorBody")
            }
        })
    }

    private fun getFilms(){
        filmCall.enqueue(object : NetworkCallback<FilmResponse>(){
            override fun onSuccess(response: FilmResponse) {
                view.showFilmList(response.results)
            }

            override fun onFailure(
                call: Call<FilmResponse>,
                statusCode: Int,
                errorBody: ResponseBody?
            ) {
                view.showFilmList(listOf())
            }

        })
    }

    override fun cancelAPIRequest() {
        profileListCall.cancel()
        filmCall.cancel()
    }
}