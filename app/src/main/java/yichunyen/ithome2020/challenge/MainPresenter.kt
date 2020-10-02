package yichunyen.ithome2020.challenge

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import yichunyen.ithome2020.challenge.data.Film
import yichunyen.ithome2020.challenge.data.FilmResponse
import yichunyen.ithome2020.challenge.data.ProfileListResponse
import yichunyen.ithome2020.challenge.network.NetworkCallback
import yichunyen.ithome2020.challenge.network.NetworkManager

private const val TAG = "MainPresenter"
class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private var filmList: List<Film> = listOf()
    private val emptyFilmString = "No film list."

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
        profileListCall.enqueue(object : NetworkCallback<ProfileListResponse>() {
            override fun onSuccess(response: ProfileListResponse) {
                view.showProfileList(response.results)
            }

            override fun onFailure(
                call: Call<ProfileListResponse>,
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
                filmList = response.results
                view.fetchedFilmListDone()
            }

            override fun onFailure(
                call: Call<FilmResponse>,
                statusCode: Int,
                errorBody: ResponseBody?
            ) {
                view.fetchedFilmListDone()
            }

        })
    }

    override fun cancelAPIRequest() {
        profileListCall.cancel()
        filmCall.cancel()
    }

    override fun getFilmDisplayString(ids: ArrayList<String>): String {
        val builder = StringBuilder()
        builder.append("Films below: ")
        if (ids.isEmpty() || filmList.isEmpty()) {
            return builder.append(emptyFilmString).toString()
        }

        builder.append("\n")
        // id starts from 1
        ids.forEach {
            try {
                val index = it.toInt() - 1
                if (index == -1 || index > filmList.size) {
                    builder.append(emptyFilmString)
                    return@forEach
                }
                builder.append("- ${filmList[index].title}\n")
            } catch (exception: Exception) {
                    Log.e(TAG, exception.message ?: "")
                }
            }
        return builder.toString()
    }
}