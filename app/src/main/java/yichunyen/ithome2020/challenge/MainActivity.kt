package yichunyen.ithome2020.challenge

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import yichunyen.ithome2020.challenge.data.Film
import yichunyen.ithome2020.challenge.data.Profile

class MainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainContract.Presenter? = null
    private var isFetchedProfileData = false
    private var isFetchedFilmList = false
    private var filmList = listOf<Film>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        setupStatusBarBackground()
        MainPresenter(this)
        progressBar.visibility = View.VISIBLE
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
        isFetchedProfileData = true
        val adapter = ProfileListAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DiverItemDecoration())
        recyclerView.adapter = adapter
        checkLoadingProgressBar()
    }

    override fun showApiError(errorMessage: String) {
        isFetchedProfileData = true
        Log.e("MainActivity", errorMessage)
        checkLoadingProgressBar()
    }

    override fun showFilmList(list: List<Film>) {
        list.forEach { Log.i("filmTitle", it.title) }
        filmList = list
        isFetchedFilmList = true
        checkLoadingProgressBar()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    private fun checkLoadingProgressBar() {
        if (isFetchedProfileData && isFetchedFilmList) {
            progressBar.visibility = View.GONE
        }
    }
}