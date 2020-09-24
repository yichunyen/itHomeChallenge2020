package yichunyen.ithome2020.challenge

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import yichunyen.ithome2020.challenge.data.Profile

class MainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        setupStatusBarBackground()
        MainPresenter(this)
        presenter?.fetchData()
    }

    private fun initToolbar() {
        toolbar.setTitle(R.string.app_name)
        toolbar.setTitleTextColor(Color.BLACK)
        toolbar.setBackgroundResource(R.color.colorPrimary)
        setSupportActionBar(toolbar)
    }

    private fun setupStatusBarBackground() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
    }

    override fun onStop() {
        super.onStop()
        presenter?.cancelAPIRequest()
    }

    override fun showProfileList(list: List<Profile>) {
        val adapter = ProfileListAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun showApiError(errorMessage: String) {
        Log.e("MainActivity", errorMessage)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}