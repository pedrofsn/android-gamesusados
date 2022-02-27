package br.com.jogosusados.features.announcement.data

import br.com.concrete.canarinho.formatador.FormatadorValor.VALOR_COM_SIMBOLO
import br.com.jogosusados.features.add.data.Owner

data class Announcement(
    val id: Long,
    val owner: Owner,
    val price: Double,
) {
    fun getLabelPrice() = VALOR_COM_SIMBOLO.formata(price.toString())
}

