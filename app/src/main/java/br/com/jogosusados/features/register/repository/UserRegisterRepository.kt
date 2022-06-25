package br.com.jogosusados.features.register.repository

import br.com.jogosusados.features.login.repository.payload.LoggedDTO
import br.com.jogosusados.features.register.data.UserRegister
import br.com.jogosusados.network.Requestable

interface UserRegisterRepository : Requestable {
    suspend fun register(user: UserRegister?): LoggedDTO?
}
