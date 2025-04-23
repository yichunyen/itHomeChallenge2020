package yichunyen.swapi.demo

import yichunyen.swapi.demo.base.BaseView
import yichunyen.swapi.demo.data.Profile

interface MainContract {
    interface View :
        BaseView<Presenter> {
        fun showProfileList(list: List<Profile>)
        fun showApiError(errorMessage: String)
    }

    interface Presenter {
        fun fetchData()
        fun cancelAPIRequest()
    }
}