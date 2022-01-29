package br.com.jogosusados.features.games.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameItem(
    val id: Long,
    val image: String,
    val title: String,
    val subtitle: String
) : Parcelable
