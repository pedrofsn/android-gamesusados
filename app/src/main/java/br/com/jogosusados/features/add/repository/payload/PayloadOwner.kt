package br.com.jogosusados.features.add.repository.payload

import br.com.jogosusados.features.add.data.Owner
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class PayloadOwner(
    val name: String?,
    val phone: String?,
    val email: String?
) : Payload<Owner> {
    override fun toModel() = Owner(
        name = extract safe name,
        phone = extract safe phone,
        email = extract safe email
    )
}
