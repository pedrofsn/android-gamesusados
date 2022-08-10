package br.com.jogosusados.features.announcement.repository.payload

data class ToReportPayload(
    val description: String,
    val id: Long,
    val type: String
)
