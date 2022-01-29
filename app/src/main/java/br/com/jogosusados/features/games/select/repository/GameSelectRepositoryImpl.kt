package br.com.jogosusados.features.games.select.repository

import br.com.jogosusados.features.games.GamesAPI
import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.callList
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class GameSelectRepositoryImpl(
    private val api: GamesAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : SelectGameRepository {

    override suspend fun getGames(idPlatform: Long): List<GameItem> {
        return (Request with api callList { getGames(idPlatform) } handled callbackNetworkRequest).orEmpty()
    }
}
