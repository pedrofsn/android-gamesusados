package br.com.jogosusados.features.add

import br.com.jogosusados.features.add.data.IdWithTitle
import br.com.jogosusados.features.games.list.GameItem

data class LabelAddGame(
    val platforms: List<IdWithTitle>,
    val game: GameItem? = null,
    val value: String = ""
)
