package br.com.jogosusados.features.settings.data

data class Profile(
    val name: String,
    val phone: String,
    val email: String,
    val type: String,
    val image: String?
)
