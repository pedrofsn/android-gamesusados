package br.com.jogosusados.features.add.data

import br.com.jogosusados.features.games.domain.payload.GameDTO
import br.com.jogosusados.features.games.list.GameItem
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class GameAnnouncementPayload(
    val id: Long?,
    val game: GameDTO?,
    val owner: OwnerPayload?,
    val price: Double?
) : Payload<GameAnnouncement> {
    override fun toModel() = GameAnnouncement(
        id = extract safe id,
        game = extract safe game ?: GameItem(id = -1, image = "", title = "", subtitle = ""),
        owner = extract safe owner ?: Owner(name = "", phone = "", email = ""),
        price = extract safe price
    )
}
