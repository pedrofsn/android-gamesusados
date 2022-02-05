package br.com.jogosusados.features.settings.repository

import retrofit2.http.GET

interface SettingsAPI {
    @GET("users/my-profile")
    suspend fun getMyProfile(): ProfileDTO
}
