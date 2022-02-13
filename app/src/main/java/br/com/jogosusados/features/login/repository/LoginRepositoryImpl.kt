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

var TOKEN_TEMP = ""
