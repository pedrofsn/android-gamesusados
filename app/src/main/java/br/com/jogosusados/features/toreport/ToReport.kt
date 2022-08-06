package br.com.jogosusados.features.toreport

import android.content.Context
import br.com.jogosusados.R
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

    fun buildTitle(context: Context) = when (type) {
        GAME -> {
            val titleGame = game?.title
            context.getString(R.string.to_report_hint_game, titleGame)
        }
        ANNOUNCEMENT -> {
            val titleAnnouncement = announcement?.owner?.name
            context.getString(R.string.to_report_hint_announcement, titleAnnouncement)
        }
    }
}
