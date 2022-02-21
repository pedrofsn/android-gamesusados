package br.com.jogosusados.features.settings.repository

import android.content.Context
import br.com.jogosusados.domain.prepareFileToUpload
import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.jogosusados.features.settings.repository.interactor.SettingsLocalInteractor
import br.com.jogosusados.features.settings.repository.interactor.SettingsRemoteInteractor
import okhttp3.MultipartBody
import java.io.File

class SettingsRepositoryImpl(
    private val local: SettingsLocalInteractor,
    private val remote: SettingsRemoteInteractor,
    private val context: Context
) : SettingsRepository {

    override suspend fun getMyProfile() = local.getMyProfile() ?: remote.getMyProfile()

    override suspend fun uploadImageProfile(file: File): ImageUploaded? {
        val body: MultipartBody.Part = context.prepareFileToUpload(file)
        return remote.uploadImageProfile(body)
    }
}
