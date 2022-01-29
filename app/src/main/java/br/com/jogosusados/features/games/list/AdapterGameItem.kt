package br.com.jogosusados.features.games.list

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ViewholderGameItemBinding
import br.com.redcode.base.mvvm.databinding.view.BaseAdapterMVVM

class AdapterGameItem(override var click: ((GameItem, Int) -> Unit)?) :
    BaseAdapterMVVM<GameItem, ViewholderGameItemBinding>() {

    override val layout: Int = R.layout.viewholder_game_item

    override fun getViewHolder(binding: ViewholderGameItemBinding) : ViewHolderGameItem {
        return ViewHolderGameItem(binding)
    }

}
