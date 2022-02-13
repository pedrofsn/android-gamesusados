package br.com.jogosusados.features.add.repository.payload

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.features.add.data.Owner
import br.com.jogosusados.features.search.repository.payloads.GameDTO
import br.com.jogosusados.features.search.data.GameItem
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
