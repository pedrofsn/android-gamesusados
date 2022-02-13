package br.com.jogosusados.features.search.domain

abstract class Paginated<T> {
    abstract val data: List<T>
    abstract val totalInAllPages: Int
}
