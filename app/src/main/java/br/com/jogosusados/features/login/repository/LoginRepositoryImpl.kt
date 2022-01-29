package br.com.jogosusados.features.login.repository

import br.com.jogosusados.features.login.repository.payload.LoggedDTO
import br.com.jogosusados.features.login.repository.payload.LoginPOST
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class LoginRepositoryImpl(
    private val api: LoginAPI,
    override val callbackNetworkRequest: CallbackNetworkRequest?,
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoggedDTO? {
        val body = LoginPOST(email, password)
        val logged = Request with api call { login(body) } handled callbackNetworkRequest
        if (logged != null) TOKEN_TEMP = logged.token
        return logged
    }
}

var TOKEN_TEMP = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJHYW1lcyBVc2Fkb3MiLCJzdWIiOiIxIiwiaWF0IjoxNjQzNDY3NDUxLCJleHAiOjE2NDM1NTM4NTF9.brSv2C_KrEm-aNDH01Fom2fJfZ_e7Jw6jGz8t8TkNNM"
