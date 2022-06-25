package br.com.jogosusados.features.register.repository

import br.com.jogosusados.features.login.repository.payload.LoggedDTO
import br.com.jogosusados.features.register.data.UserRegister
import br.com.jogosusados.features.storage.Storage
import br.com.jogosusados.features.storage.StorageConstants
import br.com.jogosusados.network.Request
import br.com.jogosusados.network.Request.call
import br.com.jogosusados.network.Request.handled
import br.com.redcode.easyreftrofit.library.CallbackNetworkRequest

class UserRegisterRepositoryImpl(
    private val api: UserRegisterAPI,
    private val storage: Storage,
    override val callbackNetworkRequest: CallbackNetworkRequest?
) : UserRegisterRepository {

    override suspend fun register(user: UserRegister?): LoggedDTO? {
        user?.toPayload()?.let { payload ->
            val logged = Request with api call { register(payload) } handled callbackNetworkRequest
            if (logged != null) storage.save(StorageConstants.TOKEN, logged.token)
            return logged
        }
        return null
    }
}
