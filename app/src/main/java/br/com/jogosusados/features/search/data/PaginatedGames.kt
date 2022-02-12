package br.com.jogosusados.features.search.data

import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.search.domain.Paginated

data class PaginatedGames(
    override val data: List<GameItem>,
    override val totalInAllPages: Int
) : Paginated<GameItem>()
