package br.com.jogosusados.connection.response


private const val TYPE = "Bearer"

class LoggedDTO(val token: String, val type: String = TYPE)