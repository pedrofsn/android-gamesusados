package br.com.jogosusados.features.add.repository

import br.com.jogosusados.features.add.data.IdWithTitle
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.callList
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class AddRepositoryImpl(
    private val api: GamesAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : AddRepository {
    override suspend fun getPlatforms(): List<IdWithTitle> {
        return (Request with api callList { getPlatforms() } handled callbackNetworkRequest).orEmpty()
    }
}
