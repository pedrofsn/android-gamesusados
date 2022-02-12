package br.com.jogosusados.features.search.repository.payloads

import br.com.jogosusados.features.games.domain.payload.GameDTO
import br.com.jogosusados.features.search.data.PaginatedGames
import br.com.redcode.base.interfaces.Payload

data class ResponseGETSearchGames(
    val content: List<GameDTO>?,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
) : Payload<PaginatedGames> {
    override fun toModel() = PaginatedGames(
        data = content?.map { it.toModel() }.orEmpty(),
        totalInAllPages = totalElements
    )
}
