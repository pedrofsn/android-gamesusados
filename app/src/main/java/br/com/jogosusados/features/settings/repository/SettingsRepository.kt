package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.features.settings.data.Profile

interface SettingsRepository {
    suspend fun getMyProfile(): Profile?
}
