package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.search.repository.payloads.GameDTO
import br.com.redcode.base.interfaces.Payload

data class ResponseGETGameAnnouncement(
    private val game: GameDTO?,
    private val announcements: List<PayloadAnnouncement>?
) : Payload<DetailGameAnnouncement> {
    override fun toModel() = DetailGameAnnouncement(
        game = game?.toModel() ?: GameItem(id = -1, image = "", title = "", subtitle = ""),
        announcements = announcements?.map { it.toModel() }.orEmpty()
    )
}
