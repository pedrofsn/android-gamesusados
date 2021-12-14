package br.com.jogosusados.features.add

import br.com.jogosusados.features.games.GameItem

data class LabelAddGame(
    val platforms: List<String>,
    val game: GameItem?,
    val value: String
)
