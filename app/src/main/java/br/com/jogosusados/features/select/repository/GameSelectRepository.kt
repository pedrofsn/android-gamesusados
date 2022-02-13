package br.com.jogosusados.features.select.repository

import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.network.Requestable

interface GameSelectRepository : Requestable {
    suspend fun getGames(idPlatform: Long): List<GameItem>
}
