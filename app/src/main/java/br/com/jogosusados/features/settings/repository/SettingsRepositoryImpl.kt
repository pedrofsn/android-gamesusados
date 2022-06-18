package br.com.jogosusados.features.settings.repository

import android.content.Context
import br.com.jogosusados.domain.prepareFileToUpload
import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.features.settings.repository.interactor.SettingsLocalInteractor
import br.com.jogosusados.features.settings.repository.interactor.SettingsRemoteInteractor
import okhttp3.MultipartBody
import java.io.File

class SettingsRepositoryImpl(
    private val local: SettingsLocalInteractor,
    private val remote: SettingsRemoteInteractor,
    private val context: Context
) : SettingsRepository {

    override suspend fun getMyProfile(): Profile? {
        return local.getMyProfile() ?: remote.getMyProfile().also { local.saveProfile(it) }
    }

    override suspend fun uploadImageProfile(file: File): ImageUploaded? {
        val body: MultipartBody.Part = context.prepareFileToUpload(file)
        val result = remote.uploadImageProfile(body)
        updateProfileImage(result)
        return result
    }

    private suspend fun updateProfileImage(result: ImageUploaded?) {
        val profileUpdated = local.getMyProfile()?.copy(image = result?.url)
        local.saveProfile(profileUpdated)
    }

    override suspend fun logout() : Boolean {
        // TODO [pedrofsn] handle remote logout
        return local.clearAll()
    }
}
