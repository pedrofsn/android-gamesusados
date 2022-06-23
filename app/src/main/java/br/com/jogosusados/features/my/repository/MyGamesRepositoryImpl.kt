package br.com.jogosusados.features.my.repository

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.callList
import br.com.jogosusados.network.Request.handled
import br.com.redcode.base.models.ErrorAPI
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyreftrofit.library.model.ErrorHandled

class MyGamesRepositoryImpl(
    private val api: MyGamesAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : MyGamesAnnouncementsRepository {

    override suspend fun getMyGamesAnnouncements(): List<GameAnnouncement> {
        val request = Request with api callList { getMyGameAnnouncements() }
        val result = request handled callbackNetworkRequest
        return result.orEmpty()
    }

    override suspend fun disableGameAnnouncement(idGameAnnouncement: Long): ErrorAPI {
        val request = Request with api call { disable(idGameAnnouncement) }
        val result: ErrorHandled? = request handled callbackNetworkRequest
        val errorAPI = result?.message?.takeIf { it.isNotBlank() }?.let { ErrorAPI(msg = it) }
        return errorAPI ?: ErrorAPI()
    }
}
