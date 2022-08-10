package br.com.jogosusados.features.announcement.repository

import br.com.jogosusados.features.toreport.ToReport
import br.com.jogosusados.network.Requestable
import br.com.redcode.base.models.ErrorAPI

interface ToReportRepository : Requestable {
    suspend fun toReport(reportData: ToReport, input: String): ErrorAPI
}
