package br.com.jogosusados.features.toreport

import br.com.jogosusados.R

enum class ToReportType(val type: String, val text: Int) {
    GAME("game", R.string.to_report_game),
    ANNOUNCEMENT("announcement", R.string.to_report_announcement),
}
