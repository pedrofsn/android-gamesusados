package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.features.settings.repository.interactor.SettingsLocalInteractor
import br.com.jogosusados.features.settings.repository.interactor.SettingsRemoteInteractor

class SettingsRepositoryImpl(
    private val local: SettingsLocalInteractor,
    private val remote: SettingsRemoteInteractor
) : SettingsRepository {

    override suspend fun getMyProfile() = local.getMyProfile() ?: remote.getMyProfile()
}
