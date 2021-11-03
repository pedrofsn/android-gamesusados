package br.com.jogosusados.features.games

import br.com.jogosusados.databinding.ViewholderGameItemBinding
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM

class ViewHolderGameItem(binding: ViewholderGameItemBinding) :
    BaseViewHolderMVVM<GameItem, ViewholderGameItemBinding>(binding) {

    override fun bind(data: GameItem) {
        binding.data = data
    }

}