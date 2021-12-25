package br.com.jogosusados.features.add.data

import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class IdWithTitlePayload(
    val id: Long?,
    val title: String?
) : Payload<IdWithTitle> {
    override fun toModel() = IdWithTitle(
        id = extract safe id,
        title = extract safe title,
    )
}
