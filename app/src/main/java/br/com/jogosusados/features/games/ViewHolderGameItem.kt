package br.com.jogosusados.features.games

import br.com.jogosusados.databinding.ViewholderGameItemBinding
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM
import br.com.redcode.easyglide.library.load

class ViewHolderGameItem(binding: ViewholderGameItemBinding) :
    BaseViewHolderMVVM<GameItem, ViewholderGameItemBinding>(binding) {

    override fun bind(data: GameItem) {
        binding.data = data
        binding.imageView.load(data.image)
    }

}
