package br.com.jogosusados.features.announcement.data

import android.os.Parcelable
import br.com.concrete.canarinho.formatador.FormatadorValor.VALOR_COM_SIMBOLO
import br.com.jogosusados.features.add.data.Owner
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Announcement(
    val id: Long,
    val owner: Owner,
    val price: Double,
): Parcelable {
    fun getLabelPrice() = VALOR_COM_SIMBOLO.formata(price.toString())
}

