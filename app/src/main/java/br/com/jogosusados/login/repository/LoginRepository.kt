package br.com.jogosusados.login.repository

import br.com.jogosusados.login.repository.payload.LoggedDTO
import br.com.jogosusados.network.Requestable

interface LoginRepository : Requestable {
    suspend fun login(email: String, password: String): LoggedDTO?
}