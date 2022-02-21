package br.com.jogosusados.features.settings.repository.payload

import br.com.jogosusados.features.settings.data.ImageUploaded
import br.com.redcode.base.extensions.extract
import br.com.redcode.base.interfaces.Payload

data class ResposePOSTImage(
    val fileName: String?,
    val folder: String?,
    val url: String?
) : Payload<ImageUploaded> {
    override fun toModel() = ImageUploaded(
        fileName = extract safe fileName,
        folder = extract safe folder,
        url = extract safe url
    )
}
