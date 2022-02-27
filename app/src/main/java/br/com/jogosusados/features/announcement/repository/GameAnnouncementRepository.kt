package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.network.Requestable

interface GameAnnouncementRepository : Requestable {
    suspend fun getGameAnnouncements(id: Long): DetailGameAnnouncement?
}
