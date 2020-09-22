package yichunyen.ithome2020.challenge

import okhttp3.ResponseBody
import retrofit2.Call
import yichunyen.ithome2020.challenge.data.ProfileList
import yichunyen.ithome2020.challenge.network.NetworkCallback
import yichunyen.ithome2020.challenge.network.NetworkManager

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    init {
        view.setPresenter(this)
    }

    private val endPointOfProfileList = NetworkManager.client.profileList()

    override fun fetchData() {
        endPointOfProfileList.enqueue(object : NetworkCallback<ProfileList>() {
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

    override fun cancelAPIRequest() {
        endPointOfProfileList.cancel()
    }
}