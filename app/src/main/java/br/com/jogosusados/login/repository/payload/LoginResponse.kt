package br.com.jogosusados.login.repository.payload

import br.com.redcode.base.interfaces.Payload


private const val TYPE = "Bearer"

data class LoginResponse(val token: String, val type: String = TYPE) : Payload<LoggedDTO> {

    override fun toModel() = LoggedDTO("", "")
}