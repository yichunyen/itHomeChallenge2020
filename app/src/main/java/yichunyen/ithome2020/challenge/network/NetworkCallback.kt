package yichunyen.ithome2020.challenge.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class NetworkCallback<T> : Callback<T> {
    abstract fun onSuccess(response: T)
    abstract fun onFailure(call: Call<T>, statusCode: Int, errorBody: ResponseBody?)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val responseBody = response.body()
        if (response.isSuccessful) {
            if (responseBody == null) {
                onFailure(call, response.code(), responseBody)
            } else {
                onSuccess(responseBody)
            }
        } else {
            handleFailureCallback(call, response.code(), response.errorBody())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        handleFailureCallback(
            call,
            -1,
            ResponseBody.create(null, t.message ?: "")
        )
    }

    private fun handleFailureCallback(
        call: Call<T>,
        httpStatusCode: Int,
        errorBody: ResponseBody?
    ) {
        onFailure(call, httpStatusCode, errorBody)
    }
}