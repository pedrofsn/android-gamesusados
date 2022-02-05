package br.com.jogosusados.features.my.repository

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.network.Requestable

interface MyGamesAnnouncementsRepository : Requestable {
    suspend fun getMyGamesAnnouncements(): List<GameAnnouncement>
}
