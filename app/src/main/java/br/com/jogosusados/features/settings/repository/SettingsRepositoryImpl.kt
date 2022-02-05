package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class SettingsRepositoryImpl(
    private val api: SettingsAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : SettingsRepository {
    override suspend fun getMyProfile(): Profile? {
        return Request with api call { getMyProfile() } handled callbackNetworkRequest
    }
}
