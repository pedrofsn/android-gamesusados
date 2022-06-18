package br.com.jogosusados.features.settings.repository.interactor

import br.com.jogosusados.features.settings.data.Profile

interface SettingsLocalInteractor {
    suspend fun getMyProfile(): Profile?
    suspend fun saveProfile(profile: Profile?)
    suspend fun clearAll(): Boolean
}
