package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class GameAnnouncementRepositoryImpl(
    private val api: GameAnnouncementsAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : GameAnnouncementRepository {

    override suspend fun getGameAnnouncements(id: Long): DetailGameAnnouncement? {
        return Request with api call { getGameAnnouncements(id) } handled callbackNetworkRequest
    }
}
