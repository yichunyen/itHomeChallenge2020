package yichunyen.ithome2020.challenge

import yichunyen.ithome2020.challenge.base.BaseView
import yichunyen.ithome2020.challenge.data.Film
import yichunyen.ithome2020.challenge.data.Profile

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showProfileList(list: List<Profile>)
        fun showApiError(errorMessage: String)
        fun fetchedFilmListDone()
    }

    interface Presenter {
        fun fetchData()
        fun cancelAPIRequest()
        fun getFilmDisplayString(ids: ArrayList<String>) : String
    }
}