package br.com.jogosusados.features.login.repository

import br.com.jogosusados.features.Api
import br.com.jogosusados.features.login.repository.payload.LoggedDTO
import br.com.jogosusados.features.login.repository.payload.LoginPOST
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class LoginRepositoryImpl(
    private val api: Api,
    override val callbackNetworkRequest: CallbackNetworkRequest?,
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoggedDTO? {
        val body = LoginPOST(email, password)
        return Request with api call { login(body) } handled callbackNetworkRequest
    }
}
