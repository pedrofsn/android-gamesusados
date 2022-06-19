package br.com.jogosusados.features.add.data

import br.com.concrete.canarinho.formatador.FormatadorValor.VALOR_COM_SIMBOLO
import br.com.jogosusados.features.search.data.GameItem

data class GameAnnouncement(
    val id: Long,
    val game: GameItem,
    val owner: Owner,
    val price: Double,
    val enabled: Boolean
) {
    fun getLabelPrice(): String = VALOR_COM_SIMBOLO.formata(price.toString())
}
