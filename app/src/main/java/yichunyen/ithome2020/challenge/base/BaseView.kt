package yichunyen.ithome2020.challenge.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
}