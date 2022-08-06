package br.com.jogosusados.features.toreport

import br.com.jogosusados.features.announcement.data.Announcement
import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.toreport.ToReportType.ANNOUNCEMENT
import br.com.jogosusados.features.toreport.ToReportType.GAME

data class ToReport(val game: GameItem?, val announcement: Announcement?) {
    val type: ToReportType
        get() = when {
            game != null -> GAME
            announcement != null -> ANNOUNCEMENT
            else -> throw RuntimeException("Tipo não suportado")
        }

    fun getTitle() = when (type) {
        GAME -> game?.title
        ANNOUNCEMENT -> announcement?.owner?.name
    }
}
