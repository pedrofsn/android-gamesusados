package br.com.jogosusados.features.settings.repository.interactor

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.repository.SettingsAPI
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import okhttp3.MultipartBody

class SettingsRemoteInteractorImpl(
    private val api: SettingsAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : SettingsRemoteInteractor {

    override suspend fun getMyProfile(): Profile? {
        return Request with api call { getMyProfile() } handled callbackNetworkRequest
    }

    override suspend fun uploadImageProfile(file: MultipartBody.Part) = Request with api call {
        uploadProfileImage(file)
    } handled callbackNetworkRequest
}
