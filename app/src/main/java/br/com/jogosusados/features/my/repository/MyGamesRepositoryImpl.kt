package br.com.jogosusados.features.my.repository

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.callList
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class MyGamesRepositoryImpl(
    private val api: MyGamesAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : MyGamesAnnouncementsRepository {
    override suspend fun getMyGamesAnnouncements(): List<GameAnnouncement> {
        return (Request with api callList { getMyGameAnnouncements() } handled callbackNetworkRequest).orEmpty()
    }
}
