package br.com.jogosusados.features.settings.repository.interactor

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.repository.interactor.SettingsConstants.PROFILE
import br.com.jogosusados.features.storage.Storage
import com.squareup.moshi.Moshi

class SettingsLocalInteractorImpl(
    private val storage: Storage,
    private val moshi: Moshi
) : SettingsLocalInteractor {

    private val adapter by lazy { moshi.adapter(Profile::class.java) }

    override suspend fun getMyProfile(): Profile? {
        val json = storage.getString(PROFILE, "")
        return if (json.isNotBlank()) adapter.fromJson(json) else null
    }
}


