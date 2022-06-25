package br.com.jogosusados.features.register.repository.payloads

data class UserRegisterPayload(
    val name: String,
    val phone: String,
    val email: String,
    val password: String
)
