package br.com.jogosusados.features.toreport

import android.os.Parcelable
import br.com.jogosusados.features.announcement.data.Announcement
import br.com.jogosusados.features.search.data.GameItem
import br.com.jogosusados.features.toreport.ToReportType.ANNOUNCEMENT
import br.com.jogosusados.features.toreport.ToReportType.GAME
import br.com.redcode.base.utils.Constants.INVALID_VALUE
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ToReport(val game: GameItem?, val announcement: Announcement?) : Parcelable {
    val type: ToReportType
        get() = when {
            game != null -> GAME
            announcement != null -> ANNOUNCEMENT
            else -> throw RuntimeException("Tipo não suportado")
        }

    fun getId() = when (type) {
        GAME -> game?.id
        ANNOUNCEMENT -> announcement?.id
    } ?: INVALID_VALUE

    fun getTitle() = when (type) {
        GAME -> game?.title
        ANNOUNCEMENT -> announcement?.owner?.name
    }
}
