package br.com.jogosusados.features.announcement.view

import br.com.jogosusados.R
import br.com.jogosusados.databinding.ActivityAnnouncementBinding
import br.com.jogosusados.features.announcement.data.Announcement
import br.com.jogosusados.features.announcement.AnnouncementViewModel
import br.com.redcode.base.mvvm.extensions.observer
import br.com.redcode.base.mvvm.restful.databinding.domain.ActivityMVVM

class AnnouncementActivity : ActivityMVVM<ActivityAnnouncementBinding, AnnouncementViewModel>() {

    override val classViewModel = AnnouncementViewModel::class.java
    override val layout = R.layout.activity_announcement

    private val observer = observer<List<Announcement>> { updateUI(it) }

    private val adapter = AdapterAnnouncement { item, position ->

    }

    override fun setupUI() {
        super.setupUI()
        viewModel.liveData.observe(this, observer)
    }

    override fun afterOnCreate() {
        enableHomeAsUpActionBar()
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun updateUI(data: List<Announcement>) {
        adapter.setCustomList(data)
        hideProgress()
    }

}
