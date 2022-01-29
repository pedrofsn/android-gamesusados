package br.com.jogosusados.features.games.select.repository

import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.network.Requestable

interface SelectGameRepository : Requestable {
    suspend fun getGames(idPlatform: Long): List<GameItem>
}
