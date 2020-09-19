package yichunyen.ithome2020.challenge

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yichunyen.ithome2020.challenge.data.ProfileList
import yichunyen.ithome2020.challenge.network.NetworkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Call profile list api
        val call = NetworkManager.client.profileList()
        call.enqueue(object : Callback<ProfileList> {
            override fun onResponse(call: Call<ProfileList>, response: Response<ProfileList>) {
                response.body()?.let {
                    it.results.forEach {
                        Log.i("MainActivity", "$it")
                    }
                }
            }

            override fun onFailure(call: Call<ProfileList>, t: Throwable) {

            }
        })
    }
}