package br.com.jogosusados.features.games.list

import android.os.Parcelable
import br.com.jogosusados.features.search.domain.WithID
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameItem(
    override val id: Long,
    val image: String,
    val title: String,
    val subtitle: String
) : Parcelable, WithID
