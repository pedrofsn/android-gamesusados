package br.com.jogosusados.features.register.repository

import br.com.jogosusados.features.login.repository.payload.LoginResponse
import br.com.jogosusados.features.register.repository.payloads.UserRegisterPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterAPI {
    @POST("users/register")
    suspend fun register(@Body body: UserRegisterPayload): LoginResponse
}
