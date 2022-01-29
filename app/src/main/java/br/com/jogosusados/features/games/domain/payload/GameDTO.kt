package br.com.jogosusados.features.games.domain.payload

import br.com.jogosusados.features.games.list.GameItem
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class GameDTO(
    val id: Long?,
    val title: String?,
    val platform: String?
) : Payload<GameItem> {
    override fun toModel() = GameItem(
        id = extract safe id,
        title = extract safe title,
        image = "",
        subtitle = extract safe platform
    )
}

