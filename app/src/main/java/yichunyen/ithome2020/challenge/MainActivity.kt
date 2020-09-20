package yichunyen.ithome2020.challenge

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import yichunyen.ithome2020.challenge.data.Profile

class MainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainPresenter(this)
        presenter?.fetchData()
    }

    override fun onStop() {
        super.onStop()
        presenter?.cancelAPIRequest()
    }

    override fun showProfileList(list: List<Profile>) {
        list.forEach {
            Log.i("MainActivity", "$it")
        }
    }

    override fun showApiError(errorMessage: String) {
        Log.e("MainActivity", errorMessage)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}