package br.com.jogosusados.features.login.repository

import br.com.jogosusados.features.Api
import br.com.jogosusados.features.login.repository.payload.LoggedDTO
import br.com.jogosusados.features.login.repository.payload.LoginPOST
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
