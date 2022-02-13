package br.com.jogosusados.features.settings.repository.interactor

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.repository.SettingsAPI
import br.com.jogosusados.features.settings.repository.interactor.SettingsConstants.PROFILE
import br.com.jogosusados.features.storage.Storage
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import com.squareup.moshi.Moshi

class RemoteInteractor(
    private val storage: Storage,
    private val moshi: Moshi,
    private val api: SettingsAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : SettingsRemoteInteractor {

    override suspend fun getMyProfile(): Profile? {
        val profile = Request with api call { getMyProfile() } handled callbackNetworkRequest
        saveProfile(profile)
        return profile
    }

    override suspend fun saveProfile(profile: Profile?) {
        if (profile != null) {
            val adapter = moshi.adapter(Profile::class.java)
            val json = adapter.toJson(profile)
            storage.save(PROFILE, json)
        }
    }
}
