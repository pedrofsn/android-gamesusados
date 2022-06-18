package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.jogosusados.features.settings.data.Profile
import java.io.File

interface SettingsRepository {
    suspend fun getMyProfile(): Profile?
    suspend fun uploadImageProfile(file: File): ImageUploaded?
    suspend fun logout(): Boolean
}
