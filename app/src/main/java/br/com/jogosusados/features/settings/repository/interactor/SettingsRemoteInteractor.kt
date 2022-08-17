package br.com.jogosusados.features.settings.repository.interactor

import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.jogosusados.features.settings.data.Profile
import br.com.jogosusados.network.Requestable
import okhttp3.MultipartBody

interface SettingsRemoteInteractor : Requestable {
    suspend fun getMyProfile(): Profile?
    suspend fun uploadImageProfile(file: MultipartBody.Part): ImageUploaded?
}
