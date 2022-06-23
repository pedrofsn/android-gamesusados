package br.com.jogosusados.features.my.repository

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.network.Requestable
import br.com.redcode.base.models.ErrorAPI

interface MyGamesAnnouncementsRepository : Requestable {
    suspend fun getMyGamesAnnouncements(): List<GameAnnouncement>
    suspend fun disableGameAnnouncement(idGameAnnouncement: Long): ErrorAPI
}
