package br.com.jogosusados.features.my

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ViewholderMyGameAnnouncementBinding
import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.redcode.base.mvvm.databinding.view.BaseAdapterMVVM

class AdapterGameAnnouncement(override var click: ((GameAnnouncement, Int) -> Unit)?) :
    BaseAdapterMVVM<GameAnnouncement, ViewholderMyGameAnnouncementBinding>() {

    override val layout: Int = R.layout.viewholder_my_game_announcement

    override fun getViewHolder(binding: ViewholderMyGameAnnouncementBinding): ViewHolderGameAnnouncement {
        return ViewHolderGameAnnouncement(binding)
    }

}
