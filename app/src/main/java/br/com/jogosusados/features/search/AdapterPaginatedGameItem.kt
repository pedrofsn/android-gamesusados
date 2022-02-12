package br.com.jogosusados.features.search

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ViewholderGameItemBinding
import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.games.list.ViewHolderGameItem
import br.com.jogosusados.features.search.domain.AdapterPaginated

class AdapterPaginatedGameItem(override var click: ((GameItem, Int) -> Unit)?) :
    AdapterPaginated<GameItem, ViewholderGameItemBinding>() {

    override val layout: Int = R.layout.viewholder_game_item

    override fun getViewHolder(binding: ViewholderGameItemBinding): ViewHolderGameItem {
        return ViewHolderGameItem(binding)
    }

}
