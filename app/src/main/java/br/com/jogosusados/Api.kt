package br.com.jogosusados

import br.com.jogosusados.login.repository.payload.LoginPOST
import br.com.jogosusados.login.repository.payload.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("auth")
    suspend fun login(@Body body: LoginPOST): LoginResponse

}