package br.com.jogosusados.network

import br.com.redcode.base.extensions.extract
import br.com.redcode.easyreftrofit.library.model.ErrorHandled

data class CustomErrorPayload(
    val message: String = "",
    val msg_dev: String = "",
    val id: String = ""
) {
    fun toModel(networkError: Int) = ErrorHandled(
        message = extract safe message,
        networkError = networkError,
        id = extract safe id
    )
}
