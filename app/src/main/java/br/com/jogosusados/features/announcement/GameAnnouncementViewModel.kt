package br.com.jogosusados.features.announcement

import br.com.jogosusados.features.announcement.repository.DetailGameAnnouncement
import br.com.jogosusados.features.announcement.repository.GameAnnouncementRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class GameAnnouncementViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<DetailGameAnnouncement>(), KoinComponent {

    private val repository: GameAnnouncementRepository by inject {
        parametersOf(this, callback)
    }

    override fun load() = process { repository.getGameAnnouncements(id) }

}
