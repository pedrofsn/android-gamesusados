package br.com.jogosusados.features.select.repository

import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.callList
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class GameSelectRepositoryImpl(
    private val api: GameSelectAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : GameSelectRepository {

    override suspend fun getGames(idPlatform: Long): List<GameItem> {
        return (Request with api callList { getGames(idPlatform) } handled callbackNetworkRequest).orEmpty()
    }
}
