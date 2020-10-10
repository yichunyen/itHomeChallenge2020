package yichunyen.swapi.demo.base

interface BaseView<T> {
    fun setPresenter(presenter: T)
}