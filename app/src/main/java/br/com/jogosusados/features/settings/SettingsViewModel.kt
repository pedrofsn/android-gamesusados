package br.com.jogosusados.features.settings

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.repository.SettingsRepository
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import br.com.redcode.easyrestful.library.extensions.process
import br.com.redcode.easyrestful.library.impl.viewmodel.BaseViewModelWithLiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class SettingsViewModel(callback: CallbackNetworkRequest?) :
    BaseViewModelWithLiveData<Profile>(), KoinComponent {

    private val repository: SettingsRepository by inject {
        parametersOf(this@SettingsViewModel, callback)
    }

    override fun load() = process { repository.getMyProfile() }
}

