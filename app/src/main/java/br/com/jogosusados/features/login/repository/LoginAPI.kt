package br.com.jogosusados.features.login.repository

import br.com.jogosusados.features.login.repository.payload.LoginPOST
import br.com.jogosusados.features.login.repository.payload.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("auth")
    suspend fun login(@Body body: LoginPOST): LoginResponse
}
