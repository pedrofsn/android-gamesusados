package br.com.jogosusados.features.announcement

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ViewholderAnnouncementBinding
import br.com.jogosusados.features.announcement.Announcement
import br.com.jogosusados.features.announcement.ViewHolderAnnouncement
import br.com.redcode.base.mvvm.databinding.view.BaseAdapterMVVM

class AdapterAnnouncement(override var click: ((Announcement, Int) -> Unit)?) :
    BaseAdapterMVVM<Announcement, ViewholderAnnouncementBinding>() {

    override val layout: Int = R.layout.viewholder_announcement

    override fun getViewHolder(binding: ViewholderAnnouncementBinding) : ViewHolderAnnouncement {
        return ViewHolderAnnouncement(binding)
    }

}