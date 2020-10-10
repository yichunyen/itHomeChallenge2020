package yichunyen.swapi.demo

import yichunyen.swapi.demo.base.BaseView
import yichunyen.swapi.demo.data.Profile

interface MainContract {
    interface View :
        BaseView<Presenter> {
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