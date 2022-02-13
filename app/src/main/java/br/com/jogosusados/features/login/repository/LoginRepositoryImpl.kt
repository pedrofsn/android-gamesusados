package br.com.jogosusados.features.login.repository

import br.com.jogosusados.features.login.repository.payload.LoggedDTO
import br.com.jogosusados.features.login.repository.payload.LoginPOST
import br.com.jogosusados.features.storage.Storage
import br.com.jogosusados.features.storage.StorageConstants.TOKEN
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class LoginRepositoryImpl(
    private val api: LoginAPI,
    private val storage: Storage,
    override val callbackNetworkRequest: CallbackNetworkRequest?,
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoggedDTO? {
        val body = LoginPOST(email, password)
        val logged = Request with api call { login(body) } handled callbackNetworkRequest
        if (logged != null) storage.save(TOKEN, logged.token)
        return logged
    }

    override suspend fun checkIfHasToken(): Boolean {
        return storage.getString(TOKEN, "").isNotBlank()
    }
}
