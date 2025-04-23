package yichunyen.swapi.demo

import okhttp3.ResponseBody
import retrofit2.Call
import yichunyen.swapi.demo.data.ProfileListResponse
import yichunyen.swapi.demo.network.NetworkCallback
import yichunyen.swapi.demo.network.NetworkManager

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    init {
        view.setPresenter(this)
    }

    private var profileListCall = NetworkManager.client.profileList()

    override fun fetchData() {
        getProfileList()
    }

    private fun getProfileList() {
        profileListCall = NetworkManager.client.profileList(1)
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

    override fun cancelAPIRequest() {
        profileListCall.cancel()
    }

}