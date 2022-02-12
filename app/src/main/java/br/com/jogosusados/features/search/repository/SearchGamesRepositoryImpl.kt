package br.com.jogosusados.features.search.repository

import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.search.domain.Paginated
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class SearchGamesRepositoryImpl(
    private val api: SearchGamesAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : SearchGamesRepository {

    override suspend fun searchGames(page: Int, title: String?): Paginated<GameItem>? {
        return Request with api call { searchGames(page, title) } handled callbackNetworkRequest
    }
}
