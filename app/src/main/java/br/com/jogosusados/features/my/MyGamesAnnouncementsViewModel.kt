package br.com.jogosusados.features.my

import br.com.jogosusados.features.my.data.LabelMyGamesAnnouncements
import br.com.jogosusados.features.my.repository.MyGamesAnnouncementsRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MyGamesAnnouncementsViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<LabelMyGamesAnnouncements>(), KoinComponent {

    private val repository: MyGamesAnnouncementsRepository by inject {
        parametersOf(this@MyGamesAnnouncementsViewModel, callback)
    }

    override fun load() = process {
        val platforms = repository.getMyGamesAnnouncements()
        LabelMyGamesAnnouncements(platforms)
    }

    fun disableGameAnnouncement(idGameAnnouncement: Long) = process("onDisabled") {
        repository.disableGameAnnouncement(idGameAnnouncement).also { load() }
    }
}
