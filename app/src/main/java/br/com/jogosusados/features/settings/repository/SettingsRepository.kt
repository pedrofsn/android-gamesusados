package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.network.Requestable

interface SettingsRepository : Requestable {
    suspend fun getMyProfile(): Profile?
}
