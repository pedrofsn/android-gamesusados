package br.com.jogosusados.features.add.data

import br.com.jogosusados.features.games.list.GameItem

data class GameAnnouncement(
    val id: Long,
    val game: GameItem,
    val owner: Owner,
    val price: Double
)
