package br.com.jogosusados.login.repository.payload


private const val TYPE = "Bearer"

class LoggedDTO(val token: String, val type: String = TYPE)