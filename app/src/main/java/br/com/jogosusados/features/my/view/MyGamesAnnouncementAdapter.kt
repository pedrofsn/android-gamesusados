package br.com.jogosusados.features.my.view

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ViewholderMyGameAnnouncementBinding
import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.redcode.base.mvvm.databinding.view.BaseAdapterMVVM
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM

class MyGamesAnnouncementAdapter(private val onLongClickListener: (GameAnnouncement) -> Unit) :
    BaseAdapterMVVM<GameAnnouncement, ViewholderMyGameAnnouncementBinding>() {

    override val layout: Int = R.layout.viewholder_my_game_announcement
    override var click: ((GameAnnouncement, Int) -> Unit)? = null

    override fun getViewHolder(binding: ViewholderMyGameAnnouncementBinding): ViewHolderMyGamesAnnouncement {
        return ViewHolderMyGamesAnnouncement(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolderMVVM<GameAnnouncement, ViewholderMyGameAnnouncementBinding>,
        position: Int
    ) {
        (holder as ViewHolderMyGamesAnnouncement).bind(
            data = items[position],
            onLongClickListener = onLongClickListener
        )
    }

}
