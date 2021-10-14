package br.com.jogosusados.connection.response


private const val TYPE = "Bearer"

class LoginResponse(val token: String, val type: String = TYPE)