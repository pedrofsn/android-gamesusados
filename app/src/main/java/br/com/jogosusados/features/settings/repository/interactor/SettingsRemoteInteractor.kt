package br.com.jogosusados.features.settings.repository.interactor

import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.network.Requestable

interface SettingsRemoteInteractor : Requestable {
    suspend fun getMyProfile(): Profile?
    suspend fun saveProfile(profile: Profile?)
}
