package br.com.jogosusados.features.search.repository

import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.search.domain.Paginated
import br.com.jogosusados.network.Requestable

interface SearchGamesRepository : Requestable {
    suspend fun searchGames(page: Int, title: String?): Paginated<GameItem>?
}
