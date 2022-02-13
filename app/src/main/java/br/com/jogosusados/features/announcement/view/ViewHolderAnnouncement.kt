package br.com.jogosusados.features.announcement.view

import br.com.jogosusados.databinding.ViewholderAnnouncementBinding
import br.com.jogosusados.features.announcement.data.Announcement
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM

class ViewHolderAnnouncement(binding: ViewholderAnnouncementBinding) :
    BaseViewHolderMVVM<Announcement, ViewholderAnnouncementBinding>(binding) {

    override fun bind(data: Announcement) {
        binding.data = data
    }

}
