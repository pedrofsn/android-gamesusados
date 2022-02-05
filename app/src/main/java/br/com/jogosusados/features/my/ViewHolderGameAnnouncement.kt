package br.com.jogosusados.features.my

import br.com.jogosusados.databinding.ViewholderMyGameAnnouncementBinding
import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM
import br.com.redcode.easyglide.library.load

class ViewHolderGameAnnouncement(binding: ViewholderMyGameAnnouncementBinding) :
    BaseViewHolderMVVM<GameAnnouncement, ViewholderMyGameAnnouncementBinding>(binding) {

    override fun bind(data: GameAnnouncement) {
        binding.data = data
        binding.imageView.load(data.game.image)
    }

}
