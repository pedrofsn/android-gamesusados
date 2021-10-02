package br.com.jogosusados.announcement

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ActivityAnnouncementBinding
import br.com.redcode.base.mvvm.restful.databinding.domain.ActivityMVVM

class AnnouncementActivity : ActivityMVVM<ActivityAnnouncementBinding, AnnouncementViewModel>() {

    override val classViewModel = AnnouncementViewModel::class.java
    override val layout = R.layout.activity_announcement

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
        viewModel.load()
    }
}