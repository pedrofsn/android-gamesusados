package br.com.jogosusados.connection.response

import br.com.redcode.base.interfaces.Payload


private const val TYPE = "Bearer"

data class LoginResponse(val token: String, val type: String = TYPE) : Payload<LoggedDTO> {

    override fun toModel() = LoggedDTO("", "")
}