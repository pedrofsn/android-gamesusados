package br.com.jogosusados.features.announcement.data

import android.os.Parcelable
import br.com.concrete.canarinho.formatador.FormatadorValor.VALOR_COM_SIMBOLO
import br.com.jogosusados.features.add.data.Owner
import br.com.jogosusados.features.search.domain.WithID
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Announcement(
    override val id: Long,
    val owner: Owner,
    val price: Double,
) : Parcelable, WithID {
    fun getLabelPrice(): String = VALOR_COM_SIMBOLO.formata(price.toString())
}

