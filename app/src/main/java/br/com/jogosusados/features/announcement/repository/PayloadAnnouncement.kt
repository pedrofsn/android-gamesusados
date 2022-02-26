package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.announcement.data.Announcement
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class PayloadAnnouncement(
    val id: Long?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val value: String
) : Payload<Announcement> {

    override fun toModel() = Announcement(
        id = extract safe id,
        name = extract safe name,
        email = extract safe email,
        phone = extract safe phone,
        value = extract safe value
    )
}
