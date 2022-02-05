package br.com.jogosusados.features.settings.repository.payload

import br.com.jogosusados.features.settings.data.Profile
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class ProfileDTO(
    val name: String?,
    val phone: String?,
    val email: String?,
    val type: String?
) : Payload<Profile> {
    override fun toModel() = Profile(
        name = extract safe name,
        phone = extract safe phone,
        email = extract safe email,
        type = extract safe type
    )
}
