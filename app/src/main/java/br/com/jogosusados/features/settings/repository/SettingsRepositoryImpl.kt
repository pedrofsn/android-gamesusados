package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.storage.Storage
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest
import com.squareup.moshi.Moshi

class SettingsRepositoryImpl(
    private val api: SettingsAPI,
    private val storage: Storage,
    private val moshi: Moshi,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : SettingsRepository {

    private val adapter by lazy { moshi.adapter(Profile::class.java) }

    override suspend fun getMyProfile(): Profile? {
        val json = storage.getString(PROFILE, "")
        return if (json.isNotBlank()) {
            adapter.fromJson(json)
        } else {
            val profile = Request with api call { getMyProfile() } handled callbackNetworkRequest
            if (profile != null) {
                val stringJson = adapter.toJson(profile)
                storage.save(PROFILE, stringJson)
            }
            profile
        }
    }

    companion object {
        private const val PROFILE = "profile"
    }
}
