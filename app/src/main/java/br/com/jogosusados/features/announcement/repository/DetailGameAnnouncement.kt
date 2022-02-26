package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.announcement.data.Announcement
import br.com.jogosusados.features.search.data.GameItem

data class DetailGameAnnouncement(
    val game: GameItem,
    val announcements: List<Announcement>
)
