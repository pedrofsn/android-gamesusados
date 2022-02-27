package br.com.jogosusados.features.announcement.repository.payload

import br.com.jogosusados.features.add.data.Owner
import br.com.jogosusados.features.add.repository.payload.PayloadOwner
import br.com.jogosusados.features.announcement.data.Announcement
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class PayloadAnnouncement(
    val id: Long?,
    val owner: PayloadOwner?,
    val price: Double
) : Payload<Announcement> {

    override fun toModel() = Announcement(
        id = extract safe id,
        owner = owner?.toModel() ?: Owner(name = "", phone = "", email = ""),
        price = extract safe price
    )
}
