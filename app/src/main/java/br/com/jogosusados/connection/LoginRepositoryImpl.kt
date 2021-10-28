package br.com.jogosusados.connection

import br.com.jogosusados.connection.bodies.LoginPOST
import br.com.jogosusados.connection.response.LoggedDTO
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class LoginRepositoryImpl(
    private val api: Api,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoggedDTO? {
        return doRequest(service = api) { api.login(LoginPOST(email, password)) }
    }
}

interface LoginRepository : Requestable {
    suspend fun login(email: String, password: String): LoggedDTO?
}

interface Requestable {
    val callbackNetworkRequest: CallbackNetworkRequest?
}