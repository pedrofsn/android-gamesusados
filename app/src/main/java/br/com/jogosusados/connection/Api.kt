package br.com.jogosusados.connection

import br.com.jogosusados.connection.bodies.LoginPOST
import br.com.jogosusados.connection.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("auth")
    fun login(@Body body: LoginPOST): LoginResponse

}