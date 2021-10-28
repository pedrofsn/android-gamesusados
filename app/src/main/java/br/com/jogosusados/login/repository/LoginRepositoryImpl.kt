package br.com.jogosusados.login.repository

import br.com.jogosusados.Api
import br.com.jogosusados.login.repository.payload.LoggedDTO
import br.com.jogosusados.login.repository.payload.LoginPOST
import br.com.jogosusados.network.doRequest
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class LoginRepositoryImpl(
    private val api: Api,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoggedDTO? {
        return doRequest(service = api) { api.login(LoginPOST(email, password)) }
    }
}
