package br.com.jogosusados.features.toreport

import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.jogosusados.features.announcement.data.Announcement

data class ToReport(val game: GameAnnouncement?, val announcement: Announcement?) {
    val type: String
        get() = when {
            game != null -> "game"
            announcement != null -> "announcement"
            else -> throw RuntimeException("Tipo não esperado")
        }
}
