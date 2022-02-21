package br.com.jogosusados.features.settings.repository

import br.com.jogosusados.features.settings.repository.payload.ProfileDTO
import br.com.jogosusados.features.settings.repository.payload.ResposePOSTImage
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SettingsAPI {

    @GET("users/my-profile")
    suspend fun getMyProfile(): ProfileDTO

    @Multipart
    @POST("images/upload/my-profile")
    suspend fun uploadProfileImage(@Part file: MultipartBody.Part): ResposePOSTImage
}
