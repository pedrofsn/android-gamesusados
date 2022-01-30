package br.com.jogosusados.features.add.repository

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.features.add.data.IdWithTitle
import br.com.jogosusados.network.Requestable

interface AddRepository : Requestable {
    suspend fun getPlatforms(): List<IdWithTitle>
    suspend fun saveAnnouncement(idGame: Long, value: String): GameAnnouncement?
}
