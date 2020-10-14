package yichunyen.swapi.demo

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import yichunyen.swapi.demo.data.Profile
import kotlin.math.abs

class MainActivity : AppCompatActivity(),
    MainContract.View {
    private var presenter: MainContract.Presenter? = null
    private var isFetchedProfileData = false
    private var isFetchedFilmList = false
    private var layoutManager: LinearLayoutManager? = null
    private var isLastPage = false
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
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
        val adapter = ProfileListAdapter(
            list,
            object :
                ProfileListAdapter.OnClickItemListener {
                override fun onClick(
                    index: Int,
                    profile: Profile
                ) {
                    presenter?.let {
                        showFilmsPopup(
                            it.getFilmDisplayString(profile.filmIds)
                        )
                    }
                }
            })
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DiverItemDecoration())
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    shouldLoadMore()
                }
            }
        })
        recyclerView.adapter = adapter
        checkLoadingProgressBar()
    }

    override fun showApiError(errorMessage: String) {
        isFetchedProfileData = true
        Log.e(TAG, errorMessage)
        checkLoadingProgressBar()
    }

    override fun fetchedFilmListDone() {
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

    private fun showFilmsPopup(films: String) {
        val dialog: AlertDialog = AlertDialog.Builder(this)
            .setMessage(films)
            .create()
        if (isFinishing) {
            return
        } else {
            dialog.show()
        }
    }

    private fun shouldLoadMore() {
        layoutManager?.let {
            if (it.findFirstCompletelyVisibleItemPosition() == RecyclerView.NO_POSITION) {
                return
            }
            val lastPosition = it.findLastCompletelyVisibleItemPosition()

            val result = abs(it.itemCount - lastPosition)
            // Determine if it is the last item
            isLastPage = (it.itemCount - 1) == lastPosition
            if (!isLastPage && abs(result) <= 8) {
                Log.i(TAG, "loadmore")
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
